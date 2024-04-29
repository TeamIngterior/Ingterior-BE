package com.team_ingterior.ingterior.member.service;

import org.springframework.stereotype.Service;

import com.team_ingterior.ingterior.member.domain.MemberResourceResponseDTO;
import com.team_ingterior.ingterior.member.mapper.MemberMapper;

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

    public void deleteMember(int memberId){
        memberMapper.deleteMember(memberId);
    }

}
