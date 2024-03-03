package com.team_ingterior.ingterior.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.DTO.member.MemberResourceResponseDTO;

@Mapper
public interface MemberMapper {

    MemberResourceResponseDTO getMemberResourceByMemberId(int memberId);
    
}