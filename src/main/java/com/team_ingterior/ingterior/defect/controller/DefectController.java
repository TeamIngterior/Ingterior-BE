package com.team_ingterior.ingterior.defect.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team_ingterior.ingterior.defect.domain.DefectsResponseDTO;
import com.team_ingterior.ingterior.defect.domain.InsertDefectRequestDTO;
import com.team_ingterior.ingterior.defect.domain.UpdateDefectRequestDTO;
import com.team_ingterior.ingterior.defect.service.DefectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "하자체크 API")
public class DefectController {
    private final DefectService defectService;

    @Operation(summary = "하자체크 생성")
    @PostMapping("/defect")
    public ResponseEntity<Integer> insertDefect(@RequestBody InsertDefectRequestDTO defect) {
        return ResponseEntity.ok().body(defectService.insertDefect(defect));
    }

    @Operation(summary = "하자체크 목록 조회")
    @GetMapping("/defect/defects")
    public ResponseEntity<List<DefectsResponseDTO>> getDefectsByConstructionId(@RequestParam int constructionId) {
        return ResponseEntity.ok().body(defectService.getDefectsByConstructionId(constructionId));
    }

    @Operation(summary = "하자체크 수정")
    @PutMapping("/defect")
    public ResponseEntity<Void> updateDefect(@RequestBody UpdateDefectRequestDTO defect){
        defectService.updateDefect(defect);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "하자체크 삭제")
    @DeleteMapping("/defect")
    public ResponseEntity<Void> deleteDefect(@RequestBody int defectId){
        defectService.deleteDefectByDefectId(defectId);
        return ResponseEntity.ok().build();
    }
    
    

    //하자 생성
    //현장의 하자 조회
    //하자 하나 조회?
    //하자 수정
    //하자 삭제
    
    
}
