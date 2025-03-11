package com.groom.sumbisori.domain.file.service;

import com.groom.sumbisori.domain.file.error.FileErrorcode;
import com.groom.sumbisori.domain.file.error.FileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.CopyObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileCopyService {
    private static final String BEFORE_PREFIX = "temp/experience/";
    private static final String AFTER_PREFIX = "private/experience/";

    private final S3Client s3Client;
    private final S3Util s3Util;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public void copy(String imageIdentifier) {
        if (!s3Util.doesObjectExist(BEFORE_PREFIX + imageIdentifier)) {
            throw new FileException(FileErrorcode.INVALID_FILE);
        }
        try {
            // 복사 요청 생성
            CopyObjectRequest copyRequest = CopyObjectRequest.builder()
                    .sourceBucket(bucketName)
                    .sourceKey(BEFORE_PREFIX + imageIdentifier)
                    .destinationBucket(bucketName)
                    .destinationKey(AFTER_PREFIX + imageIdentifier)
                    .build();

            // 파일 복사 실행
            CopyObjectResponse response = s3Client.copyObject(copyRequest);
            log.info("S3 파일 복사 성공: {}", response.copyObjectResult());
        } catch (S3Exception e) {
            log.error("S3 파일 복사 실패: {}", e.awsErrorDetails().errorMessage());
            throw new FileException(FileErrorcode.S3_ERROR);
        }
    }
}
