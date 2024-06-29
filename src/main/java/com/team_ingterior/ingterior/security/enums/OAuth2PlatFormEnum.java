package com.team_ingterior.ingterior.security.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OAuth2PlatFormEnum {
    GOOGLE("google","G","https://oauth2.googleapis.com/tokeninfo?id_token="),
    KAKAO("kakao","K","https://openapi.naver.com/v1/nid/me"),
    NAVER("naver","N","https://kapi.kakao.com/v2/user/me"),
    INSTAGRAM("instagram","I",null); 

    private final String registrationId;
    private final String platformCode;
    private final String userInfoEndpoint;


}
