package com.team_ingterior.ingterior.photo.domain;

import com.team_ingterior.ingterior.photo.enums.PhotoObjectTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class PhotoObjectDTO {
    private PhotoObjectTypeEnum objectType;
    private Integer objectId; 
}