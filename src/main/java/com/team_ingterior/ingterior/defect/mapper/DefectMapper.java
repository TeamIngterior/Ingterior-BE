package com.team_ingterior.ingterior.defect.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.defect.domain.DefectsResponseDTO;
import com.team_ingterior.ingterior.defect.domain.InsertDefectRequestDTO;
import com.team_ingterior.ingterior.defect.domain.UpdateDefectRequestDTO;

@Mapper
public interface DefectMapper {

    int insertDefect(InsertDefectRequestDTO defect);
    List<DefectsResponseDTO> getDefectsByConstructionId(int constructionId);

    void updateDefect(UpdateDefectRequestDTO defect);
    void deleteDefectByDefectId(int defectId);

}
