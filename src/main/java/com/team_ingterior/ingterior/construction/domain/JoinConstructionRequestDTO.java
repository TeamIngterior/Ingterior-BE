package com.team_ingterior.ingterior.construction.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinConstructionRequestDTO {
    @Schema(description = "사용자 ID", example = "111",required = true)
    private Integer memberId;
    @Schema(description = "현장 ID", example = "28",required = true)
    private Integer constructionId;
}
