package com.team_ingterior.ingterior.exception.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.exception.domain.ExceptionMessageDTO;


@Mapper
public interface ExceptionMessageMapper {
    ExceptionMessageDTO getExceptionMessageByCode(String code);
}
