package com.team_ingterior.ingterior.construction.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePermissionRequestDTO {
    private Integer memberId;
    private Integer targetMemberId;
    private Integer constructionId;
    private String permission;
}
