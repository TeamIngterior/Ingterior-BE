package com.team_ingterior.ingterior.service;

import org.springframework.stereotype.Service;

import com.team_ingterior.ingterior.DTO.exception.ExceptionMessageDTO;
import com.team_ingterior.ingterior.mapper.ExceptionMessageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExceptionMessageService {

    private final ExceptionMessageMapper exceptionMessageMapper;

    public ExceptionMessageDTO getExceptionMessageByCode(String code) {
        ExceptionMessageDTO returnExceptionMessage = exceptionMessageMapper.getExceptionMessageByCode(code);
        if(returnExceptionMessage == null){ //직접 발생시켰으나 DB에 없는 exception
            return 
                ExceptionMessageDTO.builder()
                .code(code)
                .devMessage("UNKNOWN_EXCEPTION")
                .userMessage("알 수 없는 오류가 발생했습니다. 개발자에게 문의해주세요.")
                .status(500)
                .build();
        }

        return returnExceptionMessage;
    }
}