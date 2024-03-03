package com.team_ingterior.ingterior.controller;

import org.springframework.web.bind.annotation.RestController;

import com.team_ingterior.ingterior.service.TestService;
import com.team_ingterior.ingterior.util.exception.DynamicException;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;


    @GetMapping("test")
    @Operation(summary = "x + y 테스트")
    public ResponseEntity<Integer> getSumXY(@RequestParam int x, int y) {
        return ResponseEntity.ok().body(testService.selectTest(x, y));
    }

    @GetMapping("exception-test")
    @Operation(summary = "exception message 테스트")
    public String get(@RequestParam String code) {
        throw new DynamicException(code);
    }
    




}