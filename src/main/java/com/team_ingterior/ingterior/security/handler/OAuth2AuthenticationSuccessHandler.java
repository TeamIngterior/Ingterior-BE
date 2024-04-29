package com.team_ingterior.ingterior.security.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import com.team_ingterior.ingterior.member.domain.UpdateRefreshTokenDTO;
import com.team_ingterior.ingterior.member.mapper.MemberMapper;
import com.team_ingterior.ingterior.security.domain.AuthToken;
import com.team_ingterior.ingterior.security.domain.CustomUser;
import com.team_ingterior.ingterior.security.service.JwtService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    private final JwtService jwtService;
    private final MemberMapper memberMapper;
    @Value("${client_redirect_uri}")
    private String CLIENT_REDIRECT_URI;
    
    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
        String userAgent = request.getHeader("User-Agent");
        CustomUser member = (CustomUser) authentication.getPrincipal();

        AuthToken authToken = jwtService.generateAuthToken(member);

        log.info("accessToken -> {}",authToken.getAccessToken());
        log.info("refreshToken -> {}",authToken.getRefreshToken());
        
        memberMapper.updateRefreshToken(
            UpdateRefreshTokenDTO.builder()
            .memberId(member.getMemberId())
            .refreshToken(authToken.getRefreshToken())
            .build()  
            );
            
        jwtService.setAuthTokenHeader(response, authToken);

        //User-Agent확인하여 웹이면 Redirect
        if(!isMobile(userAgent)){
            getRedirectStrategy().sendRedirect(request, response,jwtService.getRedirectUrl(CLIENT_REDIRECT_URI, authToken));
        }

    }
 


        private boolean isMobile(String userAgent) {
        String[] mobileKeywords = {"Mobile", "Android", "iPhone", "iPad", "Windows Phone"};
        
        for (String keyword : mobileKeywords) {
            if (userAgent.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}