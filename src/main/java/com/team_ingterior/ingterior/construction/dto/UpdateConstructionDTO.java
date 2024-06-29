package com.team_ingterior.ingterior.construction.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UpdateConstructionDTO {
    private String path;
    private Integer usage;
    private String name;
    private Integer constructionId;
    
}
