package com.team_ingterior.ingterior.exception.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ExceptionMessageDTO {
    private String code;
    private String devMessage;
    private String userMessage;
    private Integer status;

}