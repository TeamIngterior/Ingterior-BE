package com.team_ingterior.ingterior.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.member.domain.MemberDTO;
import com.team_ingterior.ingterior.member.domain.MemberResourceResponseDTO;
import com.team_ingterior.ingterior.member.domain.UpdateRefreshTokenDTO;
import com.team_ingterior.ingterior.security.domain.CustomUser;
import com.team_ingterior.ingterior.security.domain.OAuth2UserInfo;
import com.team_ingterior.ingterior.security.enums.OAuth2PlatFormEnum;

@Mapper
public interface MemberMapper {

    MemberResourceResponseDTO getMemberResourceByMemberId(int memberId);
    
    MemberDTO getMemberByEmailAndPlatform(OAuth2UserInfo info);

    void insertUserByInfo(MemberDTO info);

    Integer countMembersByPlatform(OAuth2PlatFormEnum platform);

    void updateMemberProfile(MemberDTO member);

    void updateRefreshToken(UpdateRefreshTokenDTO data);

    void deleteMember(int memberId);

    MemberDTO getMemberByRefreshToken(String refreshToken);
}