package com.team_ingterior.ingterior.common.utils;

import org.springframework.stereotype.Service;

import com.team_ingterior.ingterior.member.domain.MemberResourceResponseDTO;
import com.team_ingterior.ingterior.member.mapper.MemberMapper;
import com.team_ingterior.ingterior.security.enums.OAuth2PlatFormEnum;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CodeGenerator {
    private final MemberMapper memberMapper;

    public String generateMemberCode(OAuth2PlatFormEnum platform){

        Integer countMembersByPlatform = memberMapper.countMembersByPlatform(platform);
        //추후 버전관리시 적용이 필요함.
        int version = 1;
        String platFormCode = platform.getPlatformCode();
        char alpha = (char)('A' + (countMembersByPlatform/1000));
        String number = String.format("%03d", (countMembersByPlatform % 1000));

        String memberCode = version + platFormCode + alpha + number;

        return memberCode;
    }

    public String generateConstructionCode(int memberId){
        MemberResourceResponseDTO resource = memberMapper.getMemberResourceByMemberId(memberId);
        char alpha = 'A';
        //디코딩
        String memberCode = resource.getMemberCode();
        
        alpha = resource.getIsSubscribed()? (char)(alpha + (resource.getConstructionCount()/1000)) : 'Z';
        String number = String.format("%03d", (resource.getConstructionCount() % 1000));
        String constructionCode = memberCode + alpha + number;

        return constructionCode;
    }
    
}