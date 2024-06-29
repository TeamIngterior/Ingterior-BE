package com.team_ingterior.ingterior.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import com.team_ingterior.ingterior.security.handler.AuthenticationExceptionHandler;
import com.team_ingterior.ingterior.security.handler.OAuth2AuthenticationSuccessHandler;
import com.team_ingterior.ingterior.security.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;
import com.team_ingterior.ingterior.security.filter.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler successHandler;
    private final JwtTokenFilter jwtTokenFilter;
    private final AuthenticationExceptionHandler authenticationExceptionHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .formLogin(formLogin -> formLogin.disable())
                .authorizeHttpRequests(request -> request

                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/member/**").permitAll()
                        .requestMatchers("/token").authenticated()

                        .requestMatchers("/", "/index.html").permitAll()
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
                        .permitAll()

                        .anyRequest().permitAll())

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login(login -> {
                    login.userInfoEndpoint().userService(customOAuth2UserService);
                    login.successHandler(successHandler);
                })
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationExceptionHandler))
                .addFilterBefore(jwtTokenFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

}