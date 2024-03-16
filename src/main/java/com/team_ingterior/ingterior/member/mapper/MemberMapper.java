package com.team_ingterior.ingterior.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.member.domain.MemberResourceResponseDTO;

@Mapper
public interface MemberMapper {

    MemberResourceResponseDTO getMemberResourceByMemberId(int memberId);
    
}