package com.team_ingterior.ingterior.member.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberThumnailsResponseDTO {
    private Integer memberId;
    private String memberCode;
    private String imgUrl;
    private String permission;
}
