package com.team_ingterior.ingterior.terms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team_ingterior.ingterior.terms.domain.TermsResponseDTO;
import com.team_ingterior.ingterior.terms.mapper.TermsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TermsService {

    private final TermsMapper termsMapper;

    public List<TermsResponseDTO> getTerms(){
        return termsMapper.getTerms();
    }
    
}
