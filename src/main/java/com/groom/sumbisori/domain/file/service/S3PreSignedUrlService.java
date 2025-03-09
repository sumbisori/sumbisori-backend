package com.groom.sumbisori.domain.file.service;

import com.groom.sumbisori.domain.file.dto.FileInfo;
import com.groom.sumbisori.domain.file.dto.PreSignedUrlRequest;
import com.groom.sumbisori.domain.file.dto.PreSignedUrlResponse;
import com.groom.sumbisori.domain.file.error.FileErrorcode;
import com.groom.sumbisori.domain.file.error.FileException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
@RequiredArgsConstructor
public class S3PreSignedUrlService {
    private static final int PRE_SIGNED_URL_EXPIRATION_TIME = 3;
    private static final String S3_UPLOAD_PREFIX = "temp/experience/";

    private final S3Presigner s3PreSigner;
    private final S3Util s3Util;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    /**
     * PUT - 파일 업로드용
     */
    public List<PreSignedUrlResponse> create(PreSignedUrlRequest request) {
        return request.fileInfos().stream()
                .map(fileInfo -> {
                    FileValidator.validateImageContentType(fileInfo.contentType());
                    return getPreSignedPutUrl(fileInfo);
                })
                .toList();
    }

    private PreSignedUrlResponse getPreSignedPutUrl(FileInfo fileInfo) {
        String objectKey = S3_UPLOAD_PREFIX + UUID.randomUUID();

        // 1. S3에 업로드할 객체 요청 생성
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .contentType(fileInfo.contentType())
                .contentLength(fileInfo.size())
                .build();

        // 2. Pre-Signed URL 요청 생성
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRATION_TIME))  // URL의 유효 시간 (10분)
                .putObjectRequest(putObjectRequest)
                .build();

        // 3. Pre-Signed URL 생성
        return new PreSignedUrlResponse(s3PreSigner.presignPutObject(presignRequest).url().toExternalForm(), objectKey);
    }

    /**
     * GET - 파일 다운로드용
     */
    public URL getPreSignedGetUrl(String objectKey) {
        if (!s3Util.doesObjectExist(objectKey)) {
            throw new FileException(FileErrorcode.INVALID_FILE);
        }
        // 1. GET 방식의 S3 요청 생성
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        // 2. Pre-Signed URL 요청 생성 (유효시간 설정)
        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRATION_TIME))
                .getObjectRequest(getObjectRequest)
                .build();

        // 3. Pre-Signed URL 생성
        return s3PreSigner.presignGetObject(presignRequest).url();
    }
}
