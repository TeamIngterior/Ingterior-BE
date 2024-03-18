package com.team_ingterior.ingterior.photo.domain;

import com.team_ingterior.ingterior.photo.enums.PhotoObjectTypeEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InsertPhotoDTO {
    private PhotoObjectTypeEnum objectType;
    private Integer objectId;
    private String originalName;
    private String fileName;
    private String path;
}