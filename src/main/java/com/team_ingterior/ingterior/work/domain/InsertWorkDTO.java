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
public class InsertWorkDTO {
    private Integer constructionId;
    private String workName;
    private String workDescription;
    private String workColor;
    private Integer memberId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
}
