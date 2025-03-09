package com.groom.sumbisori.domain.file.service;

import com.groom.sumbisori.domain.file.error.FileErrorcode;
import com.groom.sumbisori.domain.file.error.FileException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

@RequiredArgsConstructor
@Component
public class S3Util {
    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public boolean doesObjectExist(String objectKey) {
        try {
            s3Client.headObject(HeadObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .build());
            return true;
        } catch (NoSuchKeyException e) {
            return false;
        } catch (Exception e) {
            throw new FileException(FileErrorcode.S3_ERROR);
        }
    }
}
