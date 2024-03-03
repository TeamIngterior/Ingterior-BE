package com.team_ingterior.ingterior.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

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

            System.out.println(fullPath+ file.getOriginalFilename());
            s3Client.putObject(new PutObjectRequest(bucket, fullPath, file.getInputStream(), objectmetadata)
                    //.withCannedAcl(CannedAccessControlList.PublicRead)
                    .withMetadata(objectmetadata));
                    

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteFile(String fullPath){
        try {
            s3Client.deleteObject(bucket, fullPath);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    
    
}
