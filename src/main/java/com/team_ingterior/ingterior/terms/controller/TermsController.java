package com.team_ingterior.ingterior.terms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team_ingterior.ingterior.terms.domain.TermsResponseDTO;
import com.team_ingterior.ingterior.terms.service.TermsService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class TermsController {
    private final TermsService termsService;

    @Operation(summary = "약관")
    @GetMapping("/terms")
    public ResponseEntity<List<TermsResponseDTO>> getTerms(){
        return ResponseEntity.ok().body(termsService.getTerms());
    }
    
}
