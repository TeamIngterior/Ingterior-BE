package com.team_ingterior.ingterior.member.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.team_ingterior.ingterior.member.service.MemberService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "사용자 API")
public class MemberController {
    private final MemberService memberService;
    
    @GetMapping("/member/{platform}")
    public void loginMember(@PathVariable String platform, HttpServletResponse response) throws IOException {
        //User-Agent 값은 변하지않음.
        response.sendRedirect("/oauth2/authorization/"+platform);
    }

    // @GetMapping("member/app")
    // public ResponseEntity<Void> getMethodName(HttpServletRequest request) {
    //     return ResponseEntity.HeadersBuilder<request>
    // }

    @GetMapping("/member/mobile/{platform}")
    public ResponseEntity<Void> loginMemberByAccessToken(@PathVariable String platform, @RequestParam String providerAccessToken) {

        return ResponseEntity.ok().build();
    }
    

    @GetMapping("/token")
    public void getMethodName() {
       log.info("요청성공");
    }
    

    @DeleteMapping("/member")
    public void deleteMember(@RequestBody int memberId){
        memberService.deleteMember(memberId);
    }
    
    @GetMapping("context-test")
    public ResponseEntity<Authentication> getMethodName1() {
        SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(SecurityContextHolder.getContext().getAuthentication());
    }
    

}
