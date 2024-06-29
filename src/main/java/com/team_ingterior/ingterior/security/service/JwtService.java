package com.team_ingterior.ingterior.security.service;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.team_ingterior.ingterior.member.domain.MemberDTO;
import com.team_ingterior.ingterior.member.domain.UpdateRefreshTokenDTO;
import com.team_ingterior.ingterior.member.mapper.MemberMapper;
import com.team_ingterior.ingterior.security.domain.AuthToken;
import com.team_ingterior.ingterior.security.domain.CustomUser;
import com.team_ingterior.ingterior.security.enums.OAuth2PlatFormEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.security.auth.message.AuthException;
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

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;
    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;
    @Value("${jwt.access.header}")
    private String accessHeader;
    @Value("${jwt.refresh.header}")
    private String refreshHeader;
    private Key secretKey;
    private final MemberMapper memberMapper;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }
    
    public AuthToken generateAuthToken(Authentication auth){
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        return generateAuthToken(customUser);
    }

    public AuthToken generateAuthToken(CustomUser customUser){
        return AuthToken.builder()
            .accessToken(this.generateAccessToken(customUser))
            .refreshToken(this.generateRefreshToken(customUser))
            .build();
    }

    public String generateRefreshToken(CustomUser customUser) {
        
        String refreshToken = generateToken(customUser, refreshTokenExpirationPeriod);

        memberMapper.updateRefreshToken(
                UpdateRefreshTokenDTO.builder()
                        .memberId(customUser.getMemberId())
                        .refreshToken(refreshToken)
                        .build());

        return refreshToken;
    }


    public String generateAccessToken(CustomUser customUser) {
        return generateToken(customUser, accessTokenExpirationPeriod);
    }

    public String generateToken(CustomUser customUser, Long expirationTime) {

        Claims claims = Jwts.claims().setSubject(customUser.getEmail());
        claims.put("memberId", customUser.getMemberId());
        claims.put("email", customUser.getEmail());
        claims.put("platform", customUser.getPlatform().toString());
        claims.put("role", customUser.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority()).collect(Collectors.toList()));

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) throws Exception{

        //verify
        parseClaims(refreshToken);


        Optional<MemberDTO> memberDTO = Optional.ofNullable(memberMapper.getMemberByRefreshToken(refreshToken));

        memberDTO.ifPresent(member ->{

            CustomUser customUser = CustomUser.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .platform(member.getPlatform())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                .build();

            setAuthTokenHeader(response, 
                AuthToken.builder()
                    .accessToken(generateAccessToken(customUser))
                    .refreshToken(generateRefreshToken(customUser))
                    .build()
            );
        });

    }

    public void checkAccessTokenAndSetAuthentication(String accessToken)throws AuthException{
        Authentication auth = getAuthentication(accessToken);

        SecurityContextHolder.getContext().setAuthentication(auth);        
    }

    public Authentication getAuthentication(String token) throws AuthException{
        Claims claims = parseClaims(token);

        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(
                claims.get("role").toString()));
		
        CustomUser principal = CustomUser.builder()
            .memberId(claims.get("memberId", Integer.class))
            .email(claims.get("email", String.class))
            .platform(OAuth2PlatFormEnum.valueOf(claims.get("platform", String.class)))
            .authorities(authorities)
            .build();

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public Claims parseClaims(String token) throws AuthException {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }


    public AuthToken getAuthToken(HttpServletRequest request){
        return AuthToken.builder()
            .accessToken(this.getToken(request,accessHeader).orElse(null))
            .refreshToken(this.getToken(request,refreshHeader).orElse(null))
            .build();
    }

    public Optional<String> getToken(HttpServletRequest request,String tokenHeaderName){
        return Optional.ofNullable(request.getHeader(tokenHeaderName))
        .filter(refreshToken -> refreshToken.startsWith(PREFIX))
        .map(refreshToken -> refreshToken.replace(PREFIX, BLANK));
    }

    public void setAuthTokenHeader(HttpServletResponse response, AuthToken authToken) {
        setAccessTokenHeader(response, authToken.getAccessToken());
        setRefreshTokenHeader(response, authToken.getRefreshToken());
    }

    public void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
        response.setHeader(accessHeader, PREFIX + accessToken);
    }

    public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
        response.setHeader(refreshHeader, PREFIX + refreshToken);
    }

    public String getRedirectUrl(final String REDIRECT_URL, AuthToken authToken) {
        return UriComponentsBuilder.fromUriString(REDIRECT_URL)
                .queryParam(accessHeader, authToken.getAccessToken())
                .queryParam(refreshHeader, authToken.getRefreshToken())
                .build().toUriString();
    }



}
