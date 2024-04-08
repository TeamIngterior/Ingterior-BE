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

    

    // //객체 비교를 위한 equals override
    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     MemberDTO memberDTO = (MemberDTO) o;
    //     return Objects.equals(memberId, memberDTO.memberId) &&
    //            Objects.equals(email, memberDTO.email) &&
    //            Objects.equals(name, memberDTO.name) &&
    //            Objects.equals(platform, memberDTO.platform);
    // }
}
