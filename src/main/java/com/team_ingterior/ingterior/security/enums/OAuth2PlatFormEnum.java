package com.team_ingterior.ingterior.security.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OAuth2PlatFormEnum {
    GOOGLE("google","G"),
    KAKAO("kakao","K"),
    NAVER("naver","N"),
    INSTAGRAM("instagram","I"); 

    private final String platformString;
    private final String platformCode;

    public String getPlatformString() {
        return platformString;
    }

    public String getPlatformCode() {
        return platformCode;
    }

}
