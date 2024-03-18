package com.team_ingterior.ingterior.photo.domain;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PhotoResponseDTO {
    private Integer photoId;
    private String originalName;
    private String fileName;
    private String path;
    private OffsetDateTime regDate;
    private OffsetDateTime updtDate;
}
