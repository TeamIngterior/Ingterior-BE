package com.team_ingterior.ingterior.DTO.construction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class JoinConstructionRequestDTO {
    private Integer memberId;
    private Integer constructionId;
}
