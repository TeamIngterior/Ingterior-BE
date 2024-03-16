package com.team_ingterior.ingterior.common.handler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.team_ingterior.ingterior.exception.domain.DynamicException;
import com.team_ingterior.ingterior.exception.domain.ExceptionMessageDTO;
import com.team_ingterior.ingterior.exception.service.ExceptionMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ExceptionMessageService messageService;

    @ExceptionHandler(DynamicException.class) // 직접 발생시킨 exception
    public ResponseEntity<Object> handleDynamicException(DynamicException ex) {
        ExceptionMessageDTO messageDto = messageService.getExceptionMessageByCode(ex.getCode());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("code", messageDto.getCode());
        body.put("dev_message", messageDto.getDevMessage());
        body.put("user_message", messageDto.getUserMessage());
        ex.printStackTrace();

        return new ResponseEntity<>(body, HttpStatus.valueOf(messageDto.getStatus()));
    }

    @ExceptionHandler(Exception.class) // 기타 exception
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("dev_message", ex.getClass());
        body.put("user_message", "알 수 없는 오류가 발생했습니다.");
        ex.printStackTrace();

        
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}