package com.team_ingterior.ingterior.construction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.construction.domain.ConstructionResponseDTO;
import com.team_ingterior.ingterior.construction.domain.ConstructionsResponseDTO;
import com.team_ingterior.ingterior.construction.domain.GetConstructionByCodeDTO;
import com.team_ingterior.ingterior.construction.domain.InsertConstructionDTO;
import com.team_ingterior.ingterior.construction.domain.JoinConstructionDTO;
import com.team_ingterior.ingterior.construction.domain.LeaveConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.LikeConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.UpdateConstructionDTO;
import com.team_ingterior.ingterior.construction.domain.UpdatePermissionRequestDTO;
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