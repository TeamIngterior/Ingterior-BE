package com.team_ingterior.ingterior.DTO.construction;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JoinConstructionDTO {
    private Integer constructionId;
    private Integer memberId;
    private String permission;
}
