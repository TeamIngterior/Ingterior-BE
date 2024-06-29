package com.team_ingterior.ingterior.member.domain;

import java.util.Objects;

import com.team_ingterior.ingterior.security.enums.OAuth2PlatFormEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class MemberDTO {
    private Integer memberId;
    private String email;
    private String name;
    private OAuth2PlatFormEnum platform;
    private String memberCode;
    private String imgUrl;

}
