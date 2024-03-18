package com.team_ingterior.ingterior.common.domain;

import org.springframework.core.io.InputStreamResource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter 

public class FileVO {
    InputStreamResource fileStream;
    String fileName;
}