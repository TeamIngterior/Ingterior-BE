package com.team_ingterior.ingterior.construction.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UpdateConstructionRequestDTO {
    private Integer memberId;
    private Integer constructionId;
    private Integer usage;
    private String constructionName;
    
}
