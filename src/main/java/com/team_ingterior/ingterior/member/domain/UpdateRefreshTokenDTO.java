package com.team_ingterior.ingterior.member.domain;

import lombok.Builder;

@Builder
public class UpdateRefreshTokenDTO {
    private Integer memberId;
    private String refreshToken;    
}
