package com.team_ingterior.ingterior.defect.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InsertDefectRequestDTO {
    @Schema(description = "현장 ID", example = "29")
    private Integer constructionId;
    @Schema(description = "좌표", example = "100,100")
    private String coordinates;
    @Schema(description = "사용자 ID", example = "111")
    private Integer memberId;
    @Schema(description = "하자 위치(제목)", example = "요깅")
    private String defectName;
    @Schema(description = "하자 설명", example = "여기에 하자가 있어용")
    private String description;
}