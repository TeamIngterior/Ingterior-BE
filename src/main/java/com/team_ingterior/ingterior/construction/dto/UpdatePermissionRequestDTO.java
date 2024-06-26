package com.team_ingterior.ingterior.construction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePermissionRequestDTO {
    @Schema(description = "사용자 ID", example = "111",required = true)
    private Integer memberId;
    @Schema(description = "대상 사용자 ID", example = "222",required = true)
    private Integer targetMemberId;
    @Schema(description = "현장 ID", example = "28",required = true)
    private Integer constructionId;
    @Schema(description = "권한", example = "2",required = true)
    private String permission;
}
