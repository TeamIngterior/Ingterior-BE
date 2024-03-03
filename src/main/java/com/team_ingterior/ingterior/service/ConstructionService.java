package com.team_ingterior.ingterior.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.team_ingterior.ingterior.DTO.construction.ConstructionsResponseDTO;
import com.team_ingterior.ingterior.DTO.construction.DeleteConstructionDTO;
import com.team_ingterior.ingterior.DTO.construction.InsertConstructionDTO;
import com.team_ingterior.ingterior.DTO.construction.InsertConstructionRequestDTO;
import com.team_ingterior.ingterior.DTO.construction.JoinConstructionDTO;
import com.team_ingterior.ingterior.DTO.construction.JoinConstructionRequestDTO;
import com.team_ingterior.ingterior.DTO.construction.LeaveConstructionDTO;
import com.team_ingterior.ingterior.DTO.member.MemberResourceResponseDTO;
import com.team_ingterior.ingterior.mapper.ConstructionMapper;
import com.team_ingterior.ingterior.util.CodeGenerator;

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
    public int deleteConstruction(DeleteConstructionDTO construction){
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
    public void leaveConstruction(LeaveConstructionDTO leave){
        constructionMapper.leaveConstruction(leave);
    }

}
