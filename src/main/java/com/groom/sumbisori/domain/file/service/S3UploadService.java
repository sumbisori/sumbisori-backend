package com.groom.sumbisori.domain.file.service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class S3UploadService {
    private static String DIRECTORY_PATH = "test/";
    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public void uploadFileToS3(InputStream inputStream, String fileName, long contentLength) {
        // 1. mark/reset 지원을 위한 BufferedInputStream 사용
        BufferedInputStream buffered = new BufferedInputStream(inputStream);

        // 2. 충분한 크기로 mark 설정 (예: contentLength 만큼)
        int markLimit = contentLength > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) contentLength;
        buffered.mark(markLimit);

        // 3. S3 업로드
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(DIRECTORY_PATH + UUID.randomUUID() + fileName)
                .contentLength(contentLength)
                .contentType(getContentType(fileName))
                .build();

        // 4. 캐시 없이 업로드
        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(buffered, contentLength));
    }

    private String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (fileExtension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            default:
                return "application/octet-stream";
        }
    }
}
