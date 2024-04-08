package com.team_ingterior.ingterior.work.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.work.domain.InsertWorkDTO;
import com.team_ingterior.ingterior.work.domain.WorkDTO;

@Mapper
public interface WorkMapper {
    void insertWork(InsertWorkDTO work);
    WorkDTO selectWorkByWorkId(WorkDTO work);
}
