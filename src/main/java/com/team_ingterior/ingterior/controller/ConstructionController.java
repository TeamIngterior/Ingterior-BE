package com.team_ingterior.ingterior.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.Response;
import com.team_ingterior.ingterior.DTO.construction.ConstructionsResponseDTO;
import com.team_ingterior.ingterior.DTO.construction.DeleteConstructionDTO;
import com.team_ingterior.ingterior.DTO.construction.InsertConstructionRequestDTO;
import com.team_ingterior.ingterior.DTO.construction.JoinConstructionDTO;
import com.team_ingterior.ingterior.DTO.construction.JoinConstructionRequestDTO;
import com.team_ingterior.ingterior.DTO.construction.LeaveConstructionDTO;
import com.team_ingterior.ingterior.service.ConstructionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;






@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ConstructionController {
    private final ConstructionService constructionService;

    @PostMapping(value = "construction", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> insertConstruction(@ModelAttribute InsertConstructionRequestDTO construction, 
        @RequestPart(required = false) MultipartFile file) {
        constructionService.insertConstruction(construction, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("constructions")
    public ResponseEntity<List<ConstructionsResponseDTO>> getConstructions(@RequestParam int memberId) {
        return ResponseEntity.ok().body(constructionService.getConsturctionsByMemberId(memberId));
    }
    
    @PutMapping("construction")
    public String updateConstruction(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }

    @DeleteMapping("construction")
    public ResponseEntity<Void> deleteConstruction(@RequestBody DeleteConstructionDTO construction) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("construction/join")
    public ResponseEntity<Void> joinConstruction(@RequestBody JoinConstructionRequestDTO join) {
        constructionService.joinConstruction(join);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("construction/leave")
    public ResponseEntity<Void> leaveConstruction(@RequestBody LeaveConstructionDTO leave) {
        constructionService.leaveConstruction(leave);        
        return ResponseEntity.ok().build();
    }
    
    
    

    
    
    
    
}