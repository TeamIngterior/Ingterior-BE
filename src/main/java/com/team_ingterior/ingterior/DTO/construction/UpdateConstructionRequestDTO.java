package com.team_ingterior.ingterior.DTO.construction;

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
