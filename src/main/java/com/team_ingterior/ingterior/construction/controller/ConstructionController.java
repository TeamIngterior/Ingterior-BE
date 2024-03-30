package com.team_ingterior.ingterior.construction.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team_ingterior.ingterior.construction.domain.ConstructionResponseDTO;
import com.team_ingterior.ingterior.construction.domain.ConstructionsResponseDTO;
import com.team_ingterior.ingterior.construction.domain.DeleteConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.InsertConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.JoinConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.LeaveConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.LikeConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.UpdateConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.UpdatePermissionRequestDTO;
import com.team_ingterior.ingterior.construction.service.ConstructionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "현장 API")
public class ConstructionController {
    private final ConstructionService constructionService;

    @Operation(summary = "현장 추가")
    @PostMapping("construction")
    public ResponseEntity<Integer> insertConstruction(InsertConstructionRequestDTO construction) {
        return ResponseEntity.ok().body(constructionService.insertConstruction(construction));
    }

    @Operation(summary = "현장 조회")
    @GetMapping("construction")
    public ResponseEntity<ConstructionResponseDTO> getConstructionByConstructionId(@RequestParam int constructionId){
        return ResponseEntity.ok().body(constructionService.getConstructionByConstructionId(constructionId));
    }

    @Operation(summary = "현장 목록 조회")
    @GetMapping("construction/constructions")
    public ResponseEntity<List<ConstructionsResponseDTO>> getConstructions(@RequestParam int memberId) {
        return ResponseEntity.ok().body(constructionService.getConsturctionsByMemberId(memberId));
    }

    @Operation(summary = "현장 수정")
    @PutMapping(value = "construction")
    public ResponseEntity<Void> updateConstruction(@RequestBody UpdateConstructionRequestDTO construction) {
        constructionService.updateConstruction(construction);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "현장 삭제")
    @DeleteMapping("construction")
    public ResponseEntity<Void> deleteConstruction(@RequestBody DeleteConstructionRequestDTO construction) {
        constructionService.deleteConstruction(construction);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "현장 참여")
    @PostMapping("construction/join")
    public ResponseEntity<Void> joinConstruction(@RequestBody JoinConstructionRequestDTO join) {
        constructionService.joinConstruction(join);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "현장 나가기")
    @DeleteMapping("construction/leave")
    public ResponseEntity<Void> leaveConstruction(@RequestBody LeaveConstructionRequestDTO leave) {
        constructionService.leaveConstruction(leave);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "현장 좋아요")
    @PostMapping("construction/like")
    public ResponseEntity<Void> postMethodName(@RequestBody LikeConstructionRequestDTO likeDTO) {
        constructionService.likeConstructionToggle(likeDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "현장 권한 수정")
    @PutMapping("construction/permission")
    public ResponseEntity<Void> putMethodName(@RequestBody UpdatePermissionRequestDTO permissionDTO) {
        constructionService.updatePermission(permissionDTO);
        return ResponseEntity.ok().build();
    }

}