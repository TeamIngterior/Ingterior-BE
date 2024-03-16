package com.team_ingterior.ingterior.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team_ingterior.ingterior.DTO.construction.ConstructionsResponseDTO;
import com.team_ingterior.ingterior.DTO.construction.DeleteConstructionRequestDTO;
import com.team_ingterior.ingterior.DTO.construction.InsertConstructionRequestDTO;
import com.team_ingterior.ingterior.DTO.construction.JoinConstructionRequestDTO;
import com.team_ingterior.ingterior.DTO.construction.LeaveConstructionRequestDTO;
import com.team_ingterior.ingterior.DTO.construction.LikeConstructionRequestDTO;
import com.team_ingterior.ingterior.DTO.construction.UpdateConstructionRequestDTO;
import com.team_ingterior.ingterior.DTO.construction.UpdatePermissionRequestDTO;
import com.team_ingterior.ingterior.service.ConstructionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;







@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ConstructionController {
    private final ConstructionService constructionService;

    @PostMapping(value = "construction", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> insertConstruction(@RequestParam int memberId, @RequestParam int usage
    , @RequestParam String constructionName, @RequestPart(required = false) MultipartFile file) {
        constructionService.insertConstruction(
            InsertConstructionRequestDTO.builder()
            .memberId(memberId)
            .constructionName(constructionName)
            .usage(usage)
            .build()
        , file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("constructions")
    public ResponseEntity<List<ConstructionsResponseDTO>> getConstructions(@RequestParam int memberId) {
        return ResponseEntity.ok().body(constructionService.getConsturctionsByMemberId(memberId));
    }
    
    @PutMapping(value = "construction", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> updateConstruction(@RequestParam int memberId, @RequestParam int constructionId
    ,@RequestParam String constructionName,@RequestParam int usage, @RequestPart(required = false) MultipartFile file) {
        constructionService.updateConstruction(
            UpdateConstructionRequestDTO.builder()
            .memberId(memberId)
            .constructionId(constructionId)
            .usage(usage)
            .constructionName(constructionName)
            .build()
        ,file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("construction")
    public ResponseEntity<Void> deleteConstruction(@RequestBody DeleteConstructionRequestDTO construction) {
        constructionService.deleteConstruction(construction);
        return ResponseEntity.ok().build();
    }

    @PostMapping("construction/join")
    public ResponseEntity<Void> joinConstruction(@RequestBody JoinConstructionRequestDTO join) {
        constructionService.joinConstruction(join);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("construction/leave")
    public ResponseEntity<Void> leaveConstruction(@RequestBody LeaveConstructionRequestDTO leave) {
        constructionService.leaveConstruction(leave);        
        return ResponseEntity.ok().build();
    }

    @PostMapping("construction/like")
    public ResponseEntity<Void> postMethodName(@RequestBody LikeConstructionRequestDTO likeDTO) {
        constructionService.likeConstructionToggle(likeDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("construction/permission")
    public ResponseEntity<Void> putMethodName(@RequestBody UpdatePermissionRequestDTO permissionDTO) {
        constructionService.updatePermission(permissionDTO);
        return ResponseEntity.ok().build();
    }
    
    
    
}