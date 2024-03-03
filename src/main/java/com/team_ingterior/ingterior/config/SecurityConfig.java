package com.team_ingterior.ingterior.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//import com.team_ingterior.ingterior.filter.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().disable()
            .authorizeHttpRequests(request -> request
                .anyRequest().permitAll());
            //.addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가
        return http.build();	// 어떠한 요청이라도 인증필요

    }   
}