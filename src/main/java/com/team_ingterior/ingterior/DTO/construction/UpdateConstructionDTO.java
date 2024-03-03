package com.team_ingterior.ingterior.DTO.construction;

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
