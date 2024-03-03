package com.team_ingterior.ingterior.DTO.construction;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteConstructionRequestDTO {
    private Integer memberId;
    private Integer constructionId;
}
