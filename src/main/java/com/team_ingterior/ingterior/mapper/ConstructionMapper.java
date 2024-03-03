package com.team_ingterior.ingterior.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.DTO.construction.ConstructionResponseDTO;
import com.team_ingterior.ingterior.DTO.construction.ConstructionsResponseDTO;
import com.team_ingterior.ingterior.DTO.construction.InsertConstructionDTO;
import com.team_ingterior.ingterior.DTO.construction.JoinConstructionDTO;
import com.team_ingterior.ingterior.DTO.construction.LeaveConstructionRequestDTO;
import com.team_ingterior.ingterior.DTO.construction.LikeConstructionRequestDTO;

@Mapper
public interface ConstructionMapper {

    int insertConstruction(InsertConstructionDTO construction);
    List<ConstructionsResponseDTO> getConsturctionsByMemberId(int memberId);
    int deleteConstruction(int constructionId);
    void joinConstruction(JoinConstructionDTO join);
    void leaveConstruction(LeaveConstructionRequestDTO leave);
    ConstructionResponseDTO getConstuctionByConstructionId(int consructionId);
    void likeConstructionToggle(LikeConstructionRequestDTO dto);
}