package com.team_ingterior.ingterior.construction.dto;

import java.time.OffsetDateTime;
import java.util.List;

import com.team_ingterior.ingterior.member.domain.MemberThumnailsResponseDTO;

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
   private String constructionName;
   private String constructionCode;
   private String memberCode;
   private OffsetDateTime regDate;
   private OffsetDateTime updtDate;
   private List<MemberThumnailsResponseDTO> memberThumnails;
   
}
