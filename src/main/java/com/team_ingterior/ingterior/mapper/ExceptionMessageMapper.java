package com.team_ingterior.ingterior.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.DTO.exception.ExceptionMessageDTO;


@Mapper
public interface ExceptionMessageMapper {
    ExceptionMessageDTO getExceptionMessageByCode(String code);
}
