package com.team_ingterior.ingterior.common.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.team_ingterior.ingterior.member.domain.MemberResourceResponseDTO;
import com.team_ingterior.ingterior.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CodeGenerator {
    private final MemberService memberService;

    public static String generateConstructionCode(MemberResourceResponseDTO resource){
        char alpha = 'A';
        //디코딩
        String memberCode = resource.getMemberCode();
        
        alpha = resource.getIsSubscribed()? (char)(alpha + (resource.getConstructionCount()/1000)) : 'Z';
        String number = String.format("%03d", (resource.getConstructionCount() % 1000));;
        String constructionCode = memberCode + alpha + number;

        return constructionCode;
    }
    
}