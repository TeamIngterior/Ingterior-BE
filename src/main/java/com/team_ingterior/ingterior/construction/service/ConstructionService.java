package com.team_ingterior.ingterior.construction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.team_ingterior.ingterior.common.aws.AwsService;
import com.team_ingterior.ingterior.common.utils.CodeGenerator;
import com.team_ingterior.ingterior.construction.domain.ConstructionPermissionEnum;
import com.team_ingterior.ingterior.construction.domain.ConstructionResponseDTO;
import com.team_ingterior.ingterior.construction.domain.ConstructionsResponseDTO;
import com.team_ingterior.ingterior.construction.domain.DeleteConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.InsertConstructionDTO;
import com.team_ingterior.ingterior.construction.domain.InsertConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.JoinConstructionDTO;
import com.team_ingterior.ingterior.construction.domain.JoinConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.LeaveConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.LikeConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.UpdateConstructionDTO;
import com.team_ingterior.ingterior.construction.domain.UpdateConstructionRequestDTO;
import com.team_ingterior.ingterior.construction.domain.UpdatePermissionRequestDTO;
import com.team_ingterior.ingterior.construction.mapper.ConstructionMapper;
import com.team_ingterior.ingterior.member.domain.MemberResourceResponseDTO;
import com.team_ingterior.ingterior.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConstructionService {
    private final ConstructionMapper constructionMapper;
    private final MemberService memberService;
    private final AwsService awsService;
    @Value("${cloud.aws.s3.construction_dir}")
    private String constructionDir;
    
    @Transactional
    public void insertConstruction(InsertConstructionRequestDTO construction, MultipartFile file){
        int memberId = construction.getMemberId();
        MemberResourceResponseDTO codeResource = memberService.getMemberResourceByMemberId(memberId);
        String constructionCode = CodeGenerator.generateConstructionCode(codeResource);
        String path = null;
        
        InsertConstructionDTO dto = InsertConstructionDTO.builder()
            .constructionName(construction.getConstructionName())
            .constructionCode(constructionCode)
            .memberId(construction.getMemberId())
            .usage(construction.getUsage())
            .build();
        

        if(file != null){
            String fileName = file.getOriginalFilename();
            path = constructionDir+"/"+constructionCode+"/"+fileName;

            dto.setPath(path);
        }
        
        int constructionId = constructionMapper.insertConstruction(dto);
        constructionMapper.joinConstruction(JoinConstructionDTO.builder()
            .constructionId(constructionId)
            .memberId(memberId)
            .permission("creator")
            .build()
        );

        if(file != null){
            awsService.uploadFile(path, file);
        }

    }


    public List<ConstructionsResponseDTO> getConsturctionsByMemberId(int memberId){
        return constructionMapper.getConsturctionsByMemberId(memberId);
    }

    @Transactional
    public void updateConstruction(UpdateConstructionRequestDTO construction, MultipartFile file){
        int memberId = construction.getMemberId();

        int constructionId = construction.getConstructionId();
        int usage = construction.getUsage();
        ConstructionResponseDTO targetConstruction = constructionMapper.getConstuctionByConstructionId(construction.getConstructionId());
        String constructionName = construction.getConstructionName();
        String path = targetConstruction.getDrawingImageUrl();

        if(file != null){
            String fileName = file.getOriginalFilename();
            path = constructionDir+"/"+targetConstruction.getCode()+"/"+fileName;
        }

        constructionMapper.updateConstruction(
            UpdateConstructionDTO.builder()
            .path(path)
            .usage(usage)
            .name(constructionName)
            .constructionId(constructionId)
            .build()
        );

        if(file != null){
            awsService.replaceFile(targetConstruction.getDrawingImageUrl() , path, file);
        }

        

    }


    @Transactional
    public int deleteConstruction(DeleteConstructionRequestDTO construction){
        int consructionId = construction.getConstructionId();
        int memberId = construction.getMemberId();

        //construction 존재 검증
        //construction 삭제권한 검증
        //ConstructionResponseDTO targetConstruction = constructionMapper.getConstuctionByConstructionId(consructionId);

        return constructionMapper.deleteConstruction(consructionId);
    }

    @Transactional
    public void joinConstruction(JoinConstructionRequestDTO join){
        constructionMapper.joinConstruction(
            JoinConstructionDTO.builder()
                .constructionId(join.getConstructionId())
                .memberId(join.getMemberId())
                .permission("viewer")
                .build()
        );
    }

    @Transactional
    public void leaveConstruction(LeaveConstructionRequestDTO leave){
        constructionMapper.leaveConstruction(leave);
    }
    
    @Transactional
    public void likeConstructionToggle(LikeConstructionRequestDTO likeDTO){
        constructionMapper.likeConstructionToggle(likeDTO);
    }

    @Transactional
    public void updatePermission(UpdatePermissionRequestDTO permissionDTO){
        int memberId = permissionDTO.getMemberId();
        // 권한 검증
        constructionMapper.updatePermission(permissionDTO);

    }

}
