package com.team_ingterior.ingterior.construction.dto;

import com.team_ingterior.ingterior.construction.enums.ConstructionPermissionEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JoinConstructionDTO {
    private Integer constructionId;
    private Integer memberId;
    private ConstructionPermissionEnum permission;
}
