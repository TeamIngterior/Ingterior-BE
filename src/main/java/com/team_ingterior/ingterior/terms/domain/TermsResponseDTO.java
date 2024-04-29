package com.team_ingterior.ingterior.terms.domain;

import lombok.Getter;

@Getter
public class TermsResponseDTO {
    private int termId;
    private String content;
    private int parentId;
    private int level;

}
