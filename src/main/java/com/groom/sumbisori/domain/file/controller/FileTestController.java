package com.groom.sumbisori.domain.file.controller;

import com.groom.sumbisori.domain.file.service.S3PreSignedUrlService;
import com.groom.sumbisori.domain.file.service.S3UploadService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//@RestController
@RequestMapping("/files/test")
@RequiredArgsConstructor
@Slf4j
public class FileTestController {
//    private final S3UploadService s3UploadService;
//    private final S3PreSignedUrlService s3PreSignedUrlService;
//    @PostMapping("/test/presigned-url")
//    public String createPreSignedUrl(@RequestBody FileController.TestPreSignedUrlRequest request) {
//        return s3PreSignedUrlService.generatePresignedUploadUrl(
//                request.fileName(),
//                request.contentType(),
//                request.fileSize()
//        );
//    }
//
//    @PostMapping("/test/multipart-file")
//    public String uploadMultipartFile(@RequestPart("file") MultipartFile file) {
//        try (InputStream inputStream = file.getInputStream()) {
//            String fileName = file.getOriginalFilename();
//            long contentLength = file.getSize();
//            s3UploadService.uploadFileToS3(inputStream, fileName, contentLength);
//            return "업로드 성공";
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return "업로드 실패: " + e.getMessage();
//        }
//    }
//
//    @PostMapping("/test/stream")
//    public String uploadStream(
//            HttpServletRequest request,
//            @RequestHeader("file-name") String fileName,
//            @RequestHeader(value = "Content-Length", required = false) Long contentLength
//    ) {
//        try (InputStream inputStream = request.getInputStream()) {
//            log.info("요청 Content-Type: {}", request.getContentType());
//            long length = (contentLength != null && contentLength > 0)
//                    ? contentLength
//                    : inputStream.available();
//            s3UploadService.uploadFileToS3(inputStream, fileName, length);
//            return "업로드 성공";
//        } catch (Exception e) {
//            log.error("업로드 실패", e);
//            return "업로드 실패: " + e.getMessage();
//        }
//    }
//
//    record TestPreSignedUrlRequest(String fileName, String contentType, Long fileSize) {
//    }
}
