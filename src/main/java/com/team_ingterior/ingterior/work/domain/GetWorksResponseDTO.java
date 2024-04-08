package com.team_ingterior.ingterior.work.domain;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class GetWorksResponseDTO {
    private Integer workId;
    private String workName;
    private String workDescription;
    private String workColor;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Boolean isCreatedByCurrentUser;
    private Integer photosCount;
}
