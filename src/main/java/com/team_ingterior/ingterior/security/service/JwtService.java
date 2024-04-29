package com.team_ingterior.ingterior.security.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.team_ingterior.ingterior.security.domain.AuthToken;
import com.team_ingterior.ingterior.security.domain.CustomUser;
import com.team_ingterior.ingterior.security.enums.OAuth2PlatFormEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@Getter
@RequiredArgsConstructor
public class JwtService {

    private final String PREFIX = "Bearer ";
    private final String BLANK = "";
    // @Value("${jwt.secret}")
    // private String secret;
    @Value("${jwt.access.expiration}")
    private long accessTokenValidationSeconds;
    @Value("${jwt.refresh.expiration}")
    private long refreshTokenValidationSeconds;
    @Value("${jwt.access.header}")
    private String accessHeader;
    @Value("${jwt.refresh.header}")
    private String refreshHeader;
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public AuthToken generateAuthToken(CustomUser customUser){
        return AuthToken.builder()
            .accessToken(this.generateAccessToken(customUser))
            .refreshToken(this.generateRefreshToken(customUser))
            .build();
    }


    public String generateRefreshToken(CustomUser customUser) {

        // 새로운 클레임 객체를 생성하고, 이메일과 역할(권한)을 셋팅
        Claims claims = Jwts.claims().setSubject("RefreshToken");
        claims.put("email", customUser.getEmail());
        claims.put("platform", customUser.getPlatform().toString());
        claims.put("role", customUser.getAuthorities().toString());

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                // 발행일자를 넣는다.
                .setIssuedAt(now)
                // 토큰의 만료일시를 설정한다.
                .setExpiration(new Date(now.getTime() + refreshTokenValidationSeconds))
                // 지정된 서명 알고리즘과 비밀 키를 사용하여 토큰을 서명한다.
                .signWith(secretKey)
                .compact();
    }

    public String generateAccessToken(CustomUser customUser) {
        Claims claims = Jwts.claims().setSubject("AccessToken");
        claims.put("email", customUser.getEmail());
        claims.put("platform", customUser.getPlatform().toString());
        claims.put("role", customUser.getAuthorities().toString());

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidationSeconds))
                .signWith(secretKey)
                .compact();

    }


    public String getEmail(String token) {
        return Jwts.parserBuilder() 
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("email", String.class); 
    }

    public String getRole(String token) {
        return Jwts.parserBuilder() 
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("role", String.class); 
    }

    public OAuth2PlatFormEnum getPlatForm(String token) {
        return OAuth2PlatFormEnum.valueOf(
            Jwts.parserBuilder() 
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("platform", String.class)
        );
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder() 
                    .setSigningKey(secretKey) 
                    .build()                
                    .parseClaimsJws(token);  
            return claims.getBody()
                    .getExpiration()
                    .after(new Date()); 
        } catch (Exception e) {
            return false; 
        }
    }
    public AuthToken getAuthToken(HttpServletRequest request){
        return AuthToken.builder()
            .accessToken(this.getAccessToken(request))
            .refreshToken(this.getRefreshToken(request))
            .build();
    }

    public String getAccessToken(HttpServletRequest request){
        return request.getHeader(accessHeader).replace(PREFIX, BLANK);
    }

    public String getRefreshToken(HttpServletRequest request){
        return request.getHeader(refreshHeader).replace(PREFIX, BLANK);
    }

    public void setAuthTokenHeader(HttpServletResponse response, AuthToken authToken) {
        setAccessTokenInHeader(response, authToken.getAccessToken());
        setRefreshTokenInHeader(response, authToken.getRefreshToken());
    }

    public void setAccessTokenInHeader(HttpServletResponse response, String accessToken) {
        response.setHeader(accessHeader, PREFIX + accessToken);
    }

    public void setRefreshTokenInHeader(HttpServletResponse response, String refreshToken) {
        response.setHeader(refreshHeader, PREFIX + refreshToken);
    }

    public String getRedirectUrl(final String REDIRECT_URL, AuthToken authToken) {
        return UriComponentsBuilder.fromUriString(REDIRECT_URL)
                .queryParam(accessHeader, authToken.getAccessToken())
                .queryParam(refreshHeader, authToken.getRefreshToken())
                .build().toUriString();
    }



}
