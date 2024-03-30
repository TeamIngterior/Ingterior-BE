package com.team_ingterior.ingterior.defect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team_ingterior.ingterior.defect.domain.DefectsResponseDTO;
import com.team_ingterior.ingterior.defect.domain.InsertDefectRequestDTO;
import com.team_ingterior.ingterior.defect.domain.UpdateDefectRequestDTO;
import com.team_ingterior.ingterior.defect.mapper.DefectMapper;
import com.team_ingterior.ingterior.photo.service.PhotoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefectService {
    private final DefectMapper defectMapper;
    private final PhotoService photoService;

    //CRUD
    public int insertDefect(InsertDefectRequestDTO defect){
        return defectMapper.insertDefect(defect);
    }

    public List<DefectsResponseDTO> getDefectsByConstructionId(int constructionId){
        return defectMapper.getDefectsByConstructionId(constructionId);
    }


    public void updateDefect(UpdateDefectRequestDTO defect){
        defectMapper.updateDefect(defect);
    }

    public void deleteDefectByDefectId(int defectId){
        
        photoService.deletePhotosByDefectId(defectId);
        defectMapper.deleteDefectByDefectId(defectId);
    }
    

    




}
