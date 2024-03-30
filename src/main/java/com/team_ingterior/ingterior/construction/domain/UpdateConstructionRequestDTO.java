package com.team_ingterior.ingterior.construction.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateConstructionRequestDTO {

    @Schema(description = "사용자 ID", example = "111",required = true)
    private Integer memberId;
    @Schema(description = "현장 ID", example = "28",required = true)
    private Integer constructionId;
    @Schema(description = "용도", example = "1",required = true)
    private Integer usage;
    @Schema(description = "현장 이름", example = "변경된 현장이름",required = true)
    private String constructionName;
    
}
