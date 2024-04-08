package com.team_ingterior.ingterior.security.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthToken {
    private String accessToken;
    private String refreshToken;
}
