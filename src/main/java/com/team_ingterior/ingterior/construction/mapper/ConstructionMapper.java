package com.team_ingterior.ingterior.construction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.construction.dto.ConstructionResponseDTO;
import com.team_ingterior.ingterior.construction.dto.ConstructionsResponseDTO;
import com.team_ingterior.ingterior.construction.dto.GetConstructionByCodeDTO;
import com.team_ingterior.ingterior.construction.dto.InsertConstructionDTO;
import com.team_ingterior.ingterior.construction.dto.JoinConstructionDTO;
import com.team_ingterior.ingterior.construction.dto.LeaveConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.dto.LikeConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.dto.UpdateConstructionDTO;
import com.team_ingterior.ingterior.construction.dto.UpdatePermissionRequestDTO;
import com.team_ingterior.ingterior.member.domain.MemberThumnailsResponseDTO;

@Mapper
public interface ConstructionMapper {

    int insertConstruction(InsertConstructionDTO construction);
    List<ConstructionsResponseDTO> getConsturctionsByMemberId(int memberId);
    ConstructionResponseDTO getConstructionByConstructionId(int constructionId);
    GetConstructionByCodeDTO getConstructionsByConstructionCode(String constructionCode);
    void updateConstruction(UpdateConstructionDTO dto);
    int deleteConstruction(int constructionId);
    void joinConstruction(JoinConstructionDTO join);
    void leaveConstruction(LeaveConstructionRequestDTO leave);
    void likeConstructionToggle(LikeConstructionRequestDTO dto);
    void updatePermission(UpdatePermissionRequestDTO dto); 
    List<MemberThumnailsResponseDTO> getMemberThumnailsByConstructionId(int constructionId);
}