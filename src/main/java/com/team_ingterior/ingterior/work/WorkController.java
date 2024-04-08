package com.team_ingterior.ingterior.work;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team_ingterior.ingterior.work.domain.GetWorkResponseDTO;
import com.team_ingterior.ingterior.work.domain.GetWorksResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "공사관리 API")
public class WorkController {

    @Operation(summary = "공사관리 생성")
    @PostMapping("/work")
    public ResponseEntity<Void> postWork(@RequestBody String entity) {
        //TODO: process POST request
        
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "공사관리 조회")
    @GetMapping("/work")
    public ResponseEntity<GetWorkResponseDTO> getWork(@RequestBody String entity) {
        //TODO: process POST request
        
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "공사관리 목록 조회")
    @GetMapping("/work/works")
    public ResponseEntity<GetWorksResponseDTO> getWorks(@RequestBody String entity) {
        //TODO: process POST request
        
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "공사관리 수정")
    @PutMapping("/work")
    public ResponseEntity<Void> updateWork(@RequestBody String entity) {
        //TODO: process POST request
        
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "공사관리 삭제")
    @DeleteMapping("/work")
    public ResponseEntity<Void> deleteWork(@RequestBody String entity) {
        //TODO: process POST request
        
        return ResponseEntity.ok().build();
    }

    
    
}