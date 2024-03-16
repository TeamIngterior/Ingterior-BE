package com.team_ingterior.ingterior.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import com.team_ingterior.ingterior.security.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

//import com.team_ingterior.ingterior.filter.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(request -> request
                .anyRequest().permitAll());
        
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
            .oauth2Login(login -> {
                login.userInfoEndpoint().userService(customOAuth2UserService);
                //login.failureHandler(null);
                //login.successHandler(null);
                });
            
            
                        //.addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가
        return http.build();	// 어떠한 요청이라도 인증필요

    }

}