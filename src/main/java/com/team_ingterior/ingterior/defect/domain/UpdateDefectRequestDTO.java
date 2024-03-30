package com.team_ingterior.ingterior.defect.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateDefectRequestDTO {
    @Schema(description = "하자체크 ID", example = "1")
    private Integer defectId;
    @Schema(description = "좌표", example = "100,100")
    private String coordinates;
    @Schema(description = "하자 위치(제목)", example = "조깅")
    private String defectName;
    @Schema(description = "하자 설명", example = "여기에 하자가 있나용")
    private String description;
}
