package com.team_ingterior.ingterior.DTO.construction;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InsertConstructionDTO {
    private String path;
    private Integer usage;
    private String constructionName;
    private String constructionCode;
    private Integer memberId;
}
