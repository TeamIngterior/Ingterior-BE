package com.team_ingterior.ingterior.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    int selectTest(int x, int y);
}
