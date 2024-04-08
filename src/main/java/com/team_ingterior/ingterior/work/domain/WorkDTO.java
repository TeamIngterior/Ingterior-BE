package com.team_ingterior.ingterior.work.domain;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class WorkDTO {
    private String workName;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String workColor;
    private Integer memberId;
    private OffsetDateTime regDate;
    private OffsetDateTime updtDate;
}
