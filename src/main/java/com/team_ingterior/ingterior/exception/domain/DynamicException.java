package com.team_ingterior.ingterior.exception.domain;

import lombok.Getter;

@Getter
public class DynamicException extends RuntimeException {
    private final String code;

    public DynamicException(String code) {
        super(code);
        this.code = code; 
    }
    

}