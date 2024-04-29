package com.team_ingterior.ingterior.terms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.terms.domain.TermsResponseDTO;

@Mapper
public interface TermsMapper {
    
    List<TermsResponseDTO> getTerms();

}
