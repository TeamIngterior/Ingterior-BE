package com.team_ingterior.ingterior.DTO.construction;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class InsertConstructionRequestDTO {
    private Integer memberId;
    private Integer usage;
    private String constructionName;
}