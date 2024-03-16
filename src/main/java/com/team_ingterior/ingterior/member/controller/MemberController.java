package com.team_ingterior.ingterior.member.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@CrossOrigin
@RestController
@RequiredArgsConstructor
public class MemberController {
    
    @GetMapping("member/{platform}")
    public void postMethodName(@PathVariable String platform, HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/"+platform);
    }

}
