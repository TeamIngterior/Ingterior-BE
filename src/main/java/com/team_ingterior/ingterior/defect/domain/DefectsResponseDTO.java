package com.team_ingterior.ingterior.defect.domain;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DefectsResponseDTO {
    private Integer defectId;
    private String geomText;
    private Integer defectOrder;
    private String defectName;
    private Integer memberId;
    private String description;
    private OffsetDateTime regDate;
    private OffsetDateTime updtDate;
}
