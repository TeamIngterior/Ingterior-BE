package com.team_ingterior.ingterior.photo.service;

import java.util.List;
import java.util.UUID;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.team_ingterior.ingterior.common.aws.AwsService;
import com.team_ingterior.ingterior.common.domain.FileVO;
import com.team_ingterior.ingterior.photo.domain.InsertPhotoDTO;
import com.team_ingterior.ingterior.photo.domain.PhotoObjectDTO;
import com.team_ingterior.ingterior.photo.domain.PhotoResponseDTO;
import com.team_ingterior.ingterior.photo.enums.PhotoObjectTypeEnum;
import com.team_ingterior.ingterior.photo.mapper.PhotoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoService {
    private String photoPath = "photo";
    private final PhotoMapper photoMapper;
    private final AwsService awsService;


    public PhotoResponseDTO getPhotoByConstructionId(int constructionId){
        return this.getPhotosByObject(new PhotoObjectDTO(PhotoObjectTypeEnum.CONSTRUCTION, constructionId)).get(0);
    }
    
    public List<PhotoResponseDTO> getPhotosByDefectId(int defectId){
        return this.getPhotosByObject(new PhotoObjectDTO(PhotoObjectTypeEnum.DEFECT, defectId));
    }

    public List<PhotoResponseDTO> getPhotosByWorkId(int workId){
        return this.getPhotosByObject(new PhotoObjectDTO(PhotoObjectTypeEnum.WORK,workId));
    }

    public List<PhotoResponseDTO> getPhotosByObject(PhotoObjectDTO object){
        return photoMapper.getPhotosByObject(object);
    }


    public PhotoResponseDTO getPhotoByPhotoId(int photoId){
        return photoMapper.getPhotoByPhotoId(photoId);
    }

    public void postConstructionPhoto(int constructionId, MultipartFile photos){
        this.postPhoto(PhotoObjectTypeEnum.CONSTRUCTION, constructionId, photos);
    }

    public void postDefectPhotos(int defectId, List<MultipartFile> photos){
        this.postPhotos(PhotoObjectTypeEnum.DEFECT, defectId, photos);
    }
    
    public void postWorkPhotos(int defectId, List<MultipartFile> photos){
        this.postPhotos(PhotoObjectTypeEnum.WORK, defectId, photos);
    }



    @Transactional
    public void postPhotos(PhotoObjectTypeEnum objectEnum, int objectId, List<MultipartFile> photos){
        //UserDetailsImpl member = SecurityUtil.getCurrentMember();

        if(photos != null && !photos.isEmpty() && photos.get(0).getSize() != 0){
            for (MultipartFile photo : photos) {
                this.postPhoto(objectEnum, objectId, photo);
            }
        log.info("photos amount: "+photos.size());        
        }

    }

    @Transactional
    public void postPhoto(PhotoObjectTypeEnum objectEnum, int objectId, MultipartFile photo){
        String originalFileName = photo.getOriginalFilename();
        String fileName = UUID.randomUUID()+"_"+originalFileName;
        String fullPath = "ingterior/" + objectEnum + "/" + objectId + "/"+photoPath + "/";

        photoMapper.insertPhoto(
            InsertPhotoDTO.builder()
            .objectType(objectEnum)
            .objectId(objectId)
            .originalName(originalFileName)
            .fileName(fileName)
            .path(fullPath)
            .build()
            );
            
        log.info("DB UPLOADED -> path = {} file_name = {}",fullPath,fileName);
        fullPath = fullPath + fileName;
        

        //익셉션 발생하면 사진 업로드 되지 않음.
        awsService.uploadFile(fullPath, photo);
    }

    @Transactional
    public void deletePhotoByPhotoIds(List<Integer> photoIds){
    
        for (Integer photoId : photoIds) {

            PhotoResponseDTO photo = photoMapper.getPhotoByPhotoId(photoId);

            String filePath = photo.getPath()+photo.getFileName();
            photoMapper.deletePhotoByPhotoId(photoId);
            awsService.deleteFile(filePath);

            log.info("DELETE : "+filePath);
            
        }

    }
    
    public FileVO downloadPhoto(int photoId){
        PhotoResponseDTO photo = photoMapper.getPhotoByPhotoId(photoId);
        String filePath = photo.getPath()+photo.getFileName();
        InputStreamResource fileStream = awsService.downloadFile(filePath);
    
        log.info("DOWNLOAD : " + filePath);
        return FileVO.builder()
                .fileStream(fileStream)
                .fileName(photo.getOriginalName())
                .build();
    }
}
