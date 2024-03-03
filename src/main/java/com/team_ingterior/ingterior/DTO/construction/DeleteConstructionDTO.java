package com.team_ingterior.ingterior.DTO.construction;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DeleteConstructionDTO {
    private Integer memberId;
    private Integer constructionId;
}
