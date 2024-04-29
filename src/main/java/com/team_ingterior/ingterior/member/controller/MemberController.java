package com.team_ingterior.ingterior.member.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "사용자 API")
public class MemberController {
    
    @GetMapping("/member/{platform}")
    public void loginMember(@PathVariable String platform, HttpServletResponse response) throws IOException {
        //User-Agent 값은 변하지않음.
        response.sendRedirect("/oauth2/authorization/"+platform);
    }

    // @GetMapping("member/app")
    // public ResponseEntity<Void> getMethodName(HttpServletRequest request) {
    //     return ResponseEntity.HeadersBuilder<request>
    // }

    @DeleteMapping("/member")
    public void deleteMember(@RequestBody int memberId){
        
    }
    

}
