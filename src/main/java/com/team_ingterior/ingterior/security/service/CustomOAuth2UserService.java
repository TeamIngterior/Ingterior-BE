package com.team_ingterior.ingterior.security.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
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
    private final ClientRegistrationRepository clientRegistrationRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(OAuth2PlatFormEnum.valueOf(registrationId.toUpperCase()), oAuth2User.getAttributes());

        MemberDTO member = memberMapper.getMemberByEmailAndPlatform(oAuth2UserInfo);

        if (member == null) {
            memberMapper.insertUserByInfo(
                MemberDTO.builder()
                    .email(oAuth2UserInfo.getEmail())
                    .name(oAuth2UserInfo.getName())
                    .platform(oAuth2UserInfo.getPlatform())
                    .memberCode(codeGenerator.generateMemberCode(oAuth2UserInfo.getPlatform()))
                    .imgUrl(oAuth2UserInfo.getPicture())
                    .build()
            );

            member = memberMapper.getMemberByEmailAndPlatform(oAuth2UserInfo);
        } else if (isNecessaryToUpdate(member, oAuth2UserInfo)) {
            memberMapper.updateMemberProfile(member);
        }

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



    public CustomUser loadUserFromToken(OAuth2PlatFormEnum platFormEnum, String providerAccessToken) {
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, providerAccessToken, null, null);
        String registrationId = platFormEnum.getRegistrationId();
        ClientRegistration clientRegistration = getClientRegistrationById(registrationId);

        OAuth2UserRequest userRequest = new OAuth2UserRequest(clientRegistration, accessToken);
        return (CustomUser)this.loadUser(userRequest);

    }

    private ClientRegistration getClientRegistrationById(String registrationId) {
        return clientRegistrationRepository.findByRegistrationId(registrationId);
    }

}
