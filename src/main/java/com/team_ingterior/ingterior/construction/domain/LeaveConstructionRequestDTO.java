package com.team_ingterior.ingterior.construction.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LeaveConstructionRequestDTO {
    private Integer memberId;
    private Integer constructionId;
}
