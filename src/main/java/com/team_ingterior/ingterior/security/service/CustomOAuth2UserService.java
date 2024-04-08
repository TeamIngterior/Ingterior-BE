package com.team_ingterior.ingterior.security.service;

import java.util.Collection;
import java.util.Collections;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.team_ingterior.ingterior.common.utils.CodeGenerator;
import com.team_ingterior.ingterior.member.domain.MemberDTO;
import com.team_ingterior.ingterior.member.mapper.MemberMapper;
import com.team_ingterior.ingterior.security.domain.CustomUser;
import com.team_ingterior.ingterior.security.domain.OAuth2UserInfo;
import com.team_ingterior.ingterior.security.enums.OAuth2PlatFormEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService{
    
    private final MemberMapper memberMapper;
    private final CodeGenerator codeGenerator;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        //platform 반환.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(OAuth2PlatFormEnum.valueOf(registrationId), oAuth2User.getAttributes());
        
        log.info("oAuth2UserInfo -> {}",oAuth2UserInfo);

        MemberDTO member = memberMapper.getMemberByEmailAndPlatform(oAuth2UserInfo);
        
        if(member == null){
            //데이터가 없을시 생성 (현재 status 1로만 찾고있음. 삭제유저는 새로 생성될 것.)
            memberMapper.insertUserByInfo(
                MemberDTO.builder()
                .email(oAuth2UserInfo.getEmail())
                .name(oAuth2UserInfo.getName())
                .platform(oAuth2UserInfo.getProvider())
                .memberCode(codeGenerator.generateMemberCode(oAuth2UserInfo.getProvider()))
                .build());
                
                member = memberMapper.getMemberByEmailAndPlatform(oAuth2UserInfo);
                log.info("member created -> {}",member.toString());

        }else if(isNecessaryToUpdate(member,oAuth2UserInfo)){
            //데이터는 있으나 변경이 필요한 경우 업데이트
            memberMapper.updateMemberProfile(member);
        }
        
        //OAuth2User 구현체 반환.
        return CustomUser.builder()
            .memberId(member.getMemberId())
            .email(member.getEmail())
            .platform(member.getPlatform())
            .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
            .build();
    }



    private boolean isNecessaryToUpdate(MemberDTO member,OAuth2UserInfo info){

        if( 
            !member.getName().equals(info.getName()) ||
            !member.getImgUrl().equals(info.getPicture())
        ){
            log.info("oAuth2UserInfo changed");
            return true;
        }

        return false;
    }

}
