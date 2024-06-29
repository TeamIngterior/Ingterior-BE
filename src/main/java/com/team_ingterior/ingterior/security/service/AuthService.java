package com.team_ingterior.ingterior.security.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.team_ingterior.ingterior.security.domain.AuthToken;
import com.team_ingterior.ingterior.security.domain.CustomUser;
import com.team_ingterior.ingterior.security.enums.OAuth2PlatFormEnum;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtService jwtService;

    public void getAuthByToken(HttpServletResponse response, String platformString, String providerAccessToken) throws AuthException{
        OAuth2PlatFormEnum platform = OAuth2PlatFormEnum.valueOf(platformString);
        CustomUser customUser = customOAuth2UserService.loadUserFromToken(platform, providerAccessToken);
        AuthToken authToken = jwtService.generateAuthToken(customUser);
        SecurityContextHolder.getContext().setAuthentication(jwtService.getAuthentication(authToken.getAccessToken()));
    }
}
