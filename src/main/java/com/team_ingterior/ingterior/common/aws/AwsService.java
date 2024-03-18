package com.team_ingterior.ingterior.common.aws;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.team_ingterior.ingterior.exception.domain.DynamicException;

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
    
    public InputStreamResource downloadFile(String fullPath){
        S3Object object = s3Client.getObject(bucket, fullPath);
        ObjectMetadata metadata = object.getObjectMetadata();
        long length = metadata.getContentLength();
        String type = metadata.getContentType();

        //log.info("Content-Type: " + metadata.getContentType());
        //log.info("Content-Length: " + metadata.getContentLength());
        if(metadata.getContentLength() == 0) {
            throw new DynamicException("F003");
        }
        return new InputStreamResource(object.getObjectContent());
    }

    
    
}
