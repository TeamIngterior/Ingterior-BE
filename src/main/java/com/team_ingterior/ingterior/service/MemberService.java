package com.team_ingterior.ingterior.service;

import org.springframework.stereotype.Service;

import com.team_ingterior.ingterior.DTO.member.MemberResourceResponseDTO;
import com.team_ingterior.ingterior.mapper.MemberMapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Service
@RequiredArgsConstructor
public class MemberService{
    private final MemberMapper memberMapper;

    public MemberResourceResponseDTO getMemberResourceByMemberId(int memberId){
        return memberMapper.getMemberResourceByMemberId(memberId);
    }

}
