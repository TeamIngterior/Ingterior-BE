package com.team_ingterior.ingterior.security.domain;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.team_ingterior.ingterior.security.enums.OAuth2PlatFormEnum;

import lombok.Builder;
import lombok.Setter;
import lombok.ToString;


@Builder
@ToString
@Setter
public class CustomUser implements OAuth2User{
    
    private Integer memberId;
    private String email;
    private OAuth2PlatFormEnum platform;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return this.email;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public OAuth2PlatFormEnum getPlatform(){
        return this.platform;
    }

    public Integer getMemberId(){
        return this.memberId;
    }
    
    public String getEmail(){
        return this.email;
    }
}
