package com.team_ingterior.ingterior.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.team_ingterior.ingterior.util.exception.DynamicException;

import lombok.RequiredArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

@Service
@RequiredArgsConstructor
public class AwsService {

    private final AmazonS3 s3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;



    public void uploadFile(String fullPath, MultipartFile file) {
        try {
            ObjectMetadata objectmetadata = new ObjectMetadata();
            objectmetadata.setContentType(file.getContentType());
            objectmetadata.setContentLength(file.getBytes().length);

            s3Client.putObject(new PutObjectRequest(bucket, fullPath, file.getInputStream(), objectmetadata)
                    //.withCannedAcl(CannedAccessControlList.PublicRead)
                    .withMetadata(objectmetadata));

        } catch (Exception e) {
            e.printStackTrace();
            throw new DynamicException("파일 업로드 실패");
        }
    }

    public void deleteFile(String fullPath){
        try {
            s3Client.deleteObject(bucket, fullPath);
        }catch (Exception e){
            e.printStackTrace();
            throw new DynamicException("파일 삭제 실패");
        }
    }

    public void replaceFile(String beforePath, String afterPath, MultipartFile file){
        this.uploadFile(afterPath, file);
        this.deleteFile(beforePath);
    }

    
    
}
