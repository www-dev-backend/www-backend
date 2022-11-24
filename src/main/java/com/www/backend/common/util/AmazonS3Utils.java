package com.www.backend.common.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class AmazonS3Utils {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    AmazonS3Client amazonS3Client;

    public String store(String fullPath, MultipartFile multipartFile) {
        try{
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(multipartFile.getContentType());
            metadata.setContentLength(multipartFile.getSize());

            amazonS3Client.putObject(new PutObjectRequest(bucket, fullPath, multipartFile.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            return amazonS3Client.getUrl(bucket, multipartFile.getOriginalFilename()).toString();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
