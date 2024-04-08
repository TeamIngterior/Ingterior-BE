package com.team_ingterior.ingterior.security.domain;

import java.util.Map;

import com.team_ingterior.ingterior.security.enums.OAuth2PlatFormEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@RequiredArgsConstructor
public class OAuth2UserInfo {
    private final OAuth2PlatFormEnum provider;
    private final String name;
    private final String email;
    private final String picture;

    public static OAuth2UserInfo of(OAuth2PlatFormEnum provider, Map<String,Object> attributes){

        switch (provider) {
            case GOOGLE:
                return ofGoogle(provider,  attributes);
            // case KAKAO:
            //     return ofKakao(provider,  attributes);
            // case NAVER:
            //     return ofNaver(provider,  attributes);
            default:
                throw new RuntimeException();
        }

    }

    private static OAuth2UserInfo ofGoogle(OAuth2PlatFormEnum provider, Map<String,Object> attributes){
        
        return OAuth2UserInfo.builder()
            .provider(provider)
            .email((String)attributes.get("email"))
            .name((String)attributes.get("name"))
            .picture((String)attributes.get("picture"))
            .build();
    }


}