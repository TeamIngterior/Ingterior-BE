package com.team_ingterior.ingterior.DTO.construction;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class LeaveConstructionDTO {
    private int constructionId;
    private int memberId;
}
