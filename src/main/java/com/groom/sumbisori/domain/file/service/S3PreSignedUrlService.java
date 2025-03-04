package com.groom.sumbisori.domain.file.service;

import com.groom.sumbisori.domain.file.dto.FileInfo;
import com.groom.sumbisori.domain.file.dto.PreSignedUrlRequest;
import com.groom.sumbisori.domain.file.dto.PreSignedUrlResponse;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
public class S3PreSignedUrlService {
    private static final int PRE_SIGNED_URL_EXPIRATION_TIME = 3;
    private static final String S3_UPLOAD_PREFIX = "temp/experience/";

    private final S3Presigner s3PreSigner;
    private final String bucketName;

    public S3PreSignedUrlService(S3Presigner s3PreSigner,
                                 @Value("${cloud.aws.s3.bucket}") String bucketName) {
        this.s3PreSigner = s3PreSigner;
        this.bucketName = bucketName;
    }

    public List<PreSignedUrlResponse> create(PreSignedUrlRequest request) {
        return request.fileInfos().stream()
                .map(fileInfo -> {
                    FileValidator.validateImageContentType(fileInfo.contentType());
                    return getPreSignedUrl(fileInfo);
                })
                .toList();
    }

    private PreSignedUrlResponse getPreSignedUrl(FileInfo fileInfo) {
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
}
