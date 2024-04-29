package com.team_ingterior.ingterior.construction.domain;

import java.time.OffsetDateTime;
import java.util.List;

import com.team_ingterior.ingterior.member.domain.MemberThumnailsResponseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter
public class GetConstructionByCodeDTO {
    private int constructionId;
    private String constructionName;
    private String constructioCode;
    private String memberCode;
    private OffsetDateTime regDate;
    private List<MemberThumnailsResponseDTO> memberThumnails;

}
