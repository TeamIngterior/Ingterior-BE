package com.team_ingterior.ingterior.security.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtService jwtService;
    @Value("${client_redirect_uri}")
    private String CLIENT_REDIRECT_URI;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        AuthToken authToken = jwtService.generateAuthToken(authentication);

        log.info("accessToken -> {}", authToken.getAccessToken());
        log.info("refreshToken -> {}", authToken.getRefreshToken());

        jwtService.setAuthTokenHeader(response, authToken);

        // 기존의 앱,웹 분기는 api 분기로 변경.
        getRedirectStrategy().sendRedirect(request, response,
                jwtService.getRedirectUrl(CLIENT_REDIRECT_URI, authToken));

    }

}