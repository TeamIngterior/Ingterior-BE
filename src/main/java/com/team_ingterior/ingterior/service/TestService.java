package com.team_ingterior.ingterior.service;

import org.springframework.stereotype.Service;

import com.team_ingterior.ingterior.mapper.TestMapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Service
@RequiredArgsConstructor
public class TestService{
    private final TestMapper testMapper;


    public int selectTest(int x, int y){
        return testMapper.selectTest(x,y);
    }
}
