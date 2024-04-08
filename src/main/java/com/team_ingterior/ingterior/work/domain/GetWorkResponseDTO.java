package com.team_ingterior.ingterior.work.domain;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class GetWorkResponseDTO {
    private String workName;
    private String workDescription;
    private String workColor;
    private String mainPhotoUrl;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private OffsetDateTime regDate;
    private OffsetDateTime updtDate;
    private Boolean isCreatedByCurrentUser;
    private List<String> subPhotoUrls;
}
