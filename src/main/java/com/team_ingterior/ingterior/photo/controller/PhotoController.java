package com.team_ingterior.ingterior.photo.controller;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team_ingterior.ingterior.common.domain.FileVO;
import com.team_ingterior.ingterior.common.utils.FileUtil;
import com.team_ingterior.ingterior.photo.domain.PhotoResponseDTO;
import com.team_ingterior.ingterior.photo.service.PhotoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;



@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "사진 API")
public class PhotoController {
    private final PhotoService photoService;

    @Operation(summary = "현장 사진 업로드")
    @PostMapping(value = "photo/construction", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> postConstructionPhoto(@RequestParam("constructionId") int constructionId,
        @RequestPart("photo") MultipartFile photo) {
            photoService.postConstructionPhoto(constructionId, photo);
            return ResponseEntity.ok().build();
    }

    @Operation(summary = "하자체크 사진 업로드")
    @PostMapping(value = "photo/defect", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> postDefectPhotos(@RequestParam("defectId") int defectId,
        @RequestPart("photos") List<MultipartFile> photos) {
            photoService.postDefectPhotos(defectId, photos);
            return ResponseEntity.ok().build();
    }

    @Operation(summary = "공사관리 사진 업로드")
    @PostMapping(value = "photo/work", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> postWorkPhotos(@RequestParam("workId") int workId, 
        @RequestPart("photos") List<MultipartFile> photos) {
            photoService.postWorkPhotos(workId, photos);
            return ResponseEntity.ok().build();
    }

    @Operation(summary = "공사관리 사진 조회")
    @GetMapping("photo/construction")
    public ResponseEntity<PhotoResponseDTO> getPhotoByConstructionId(@RequestParam int constructionId) {
        return ResponseEntity.ok().body(photoService.getPhotoByConstructionId(constructionId));
    }

    @Operation(summary = "하자체크 사진목록 조회")
    @GetMapping("photo/defect")
    public ResponseEntity<List<PhotoResponseDTO>> getPhotosByDefectId(@RequestParam int defectId) {
        return ResponseEntity.ok().body(photoService.getPhotosByDefectId(defectId));
    }

    @Operation(summary = "공사관리 사진 조회")
    @GetMapping("photo/work")
    public ResponseEntity<List<PhotoResponseDTO>> getPhotosByWorkId(@RequestParam int workId) {
        return ResponseEntity.ok().body(photoService.getPhotosByWorkId(workId));
    }

    @Operation(summary = "사진ID로 사진 삭제")
    @DeleteMapping("photo")
    public ResponseEntity<Void> deletePhotoByPhotoIds(List<Integer> photoIds){
        photoService.deletePhotoByPhotoIds(photoIds);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "사진 다운로드")
    @GetMapping("/photo/download")
    public ResponseEntity<InputStreamResource> downloadPhoto(@RequestParam int photoId) {
        FileVO fileVO = photoService.downloadPhoto(photoId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, FileUtil.encodeContentDisposition(fileVO.getFileName()))
                .body(fileVO.getFileStream());
    }
    
    

}
