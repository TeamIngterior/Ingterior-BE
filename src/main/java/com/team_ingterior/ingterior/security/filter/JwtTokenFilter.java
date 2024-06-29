package com.team_ingterior.ingterior.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.team_ingterior.ingterior.security.domain.AuthToken;
import com.team_ingterior.ingterior.security.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String NO_CHECK_URL = "/login";

    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{

        if (request.getRequestURI().equals(NO_CHECK_URL)) {
            filterChain.doFilter(request, response); 
            return; 
        }

        AuthToken authToken = jwtService.getAuthToken(request);

        String accessToken = authToken.getAccessToken();
        String refreshToken = authToken.getRefreshToken();

        
        try {
            if(refreshToken != null && accessToken == null){
                jwtService.checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
                return;
            } else if(accessToken != null && refreshToken == null){
                jwtService.checkAccessTokenAndSetAuthentication(accessToken);
                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            log.error("JWT processing failed: {}", e.getMessage(),e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is invalid or expired");
        }

    }

}
