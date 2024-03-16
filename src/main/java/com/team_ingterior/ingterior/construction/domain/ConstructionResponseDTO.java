package com.team_ingterior.ingterior.construction.domain;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ConstructionResponseDTO {
   private Integer constructionId;
   private String drawingImageUrl;
   private Integer usage;
   private String name;
   private String code;
   private Integer creatorId;
   private OffsetDateTime regDate;
   private OffsetDateTime updtDate;
   
}
