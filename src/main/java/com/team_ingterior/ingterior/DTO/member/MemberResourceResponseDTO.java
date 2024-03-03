package com.team_ingterior.ingterior.DTO.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MemberResourceResponseDTO {
    private String memberCode;
    private Boolean isSubscribed;
    private Integer constructionCount;
    
}
