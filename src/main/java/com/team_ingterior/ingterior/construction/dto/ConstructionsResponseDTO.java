package com.team_ingterior.ingterior.construction.dto;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ConstructionsResponseDTO {
    private Integer constructionId;
    private Integer usage;
    private String constructionName;
    private String constructionCode;
    private String memberCode;
    private boolean isLiked;
    private boolean isCreator;
    private OffsetDateTime regDate;
    private List<String> memberImgUrls;

}
