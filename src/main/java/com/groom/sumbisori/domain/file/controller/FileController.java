package com.groom.sumbisori.domain.file.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.domain.file.dto.PreSignedUrlResponse;
import com.groom.sumbisori.domain.file.dto.S3FileResponse;
import com.groom.sumbisori.domain.file.dto.SeafoodRecognitionResponse;
import com.groom.sumbisori.domain.file.dto.request.PreSignedUrlRequest;
import com.groom.sumbisori.domain.file.service.FileLookupService;
import com.groom.sumbisori.domain.file.service.ImageAnalyzeService;
import com.groom.sumbisori.domain.file.service.S3PreSignedUrlService;
import com.groom.sumbisori.domain.file.service.S3UploadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Slf4j
public class FileController implements FileApi {
    private final S3PreSignedUrlService s3PreSignedUrlService;
    private final ImageAnalyzeService imageAnalyzeService;
    private final FileLookupService fileLookupService;
    private final S3UploadService s3UploadService;

    @GetMapping("/{imageIdentifier}")
    public ResponseEntity<byte[]> getFileImage(@LoginUser Long userId, @PathVariable String imageIdentifier) {
        S3FileResponse s3FileResponse = fileLookupService.lookup(userId, imageIdentifier);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(s3FileResponse.contentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .header(HttpHeaders.CACHE_CONTROL, "private, max-age=86400, no-transform")
                .header("X-Content-Type-Options", "nosniff")
                .body(s3FileResponse.data());
    }

    @PostMapping("/presigned-url")
    public ResponseEntity<List<PreSignedUrlResponse>> createPreSignedUrl(
            @Valid @RequestBody PreSignedUrlRequest request) {
        return ResponseEntity.ok().body(s3PreSignedUrlService.create(request));
    }

    @PostMapping("/test/presigned-url")
    public String createPreSignedUrl(@RequestBody TestPreSignedUrlRequest request) {
        return s3PreSignedUrlService.generatePresignedUploadUrl(
                request.fileName(),
                request.contentType(),
                request.fileSize()
        );
    }

    @GetMapping("/analyze")
    public ResponseEntity<List<SeafoodRecognitionResponse>> imageAnalyze(@RequestParam String imageIdentifier) {
        return ResponseEntity.ok(imageAnalyzeService.analyze(imageIdentifier));
    }

    @PostMapping("/multipart-file")
    public String uploadMultipartFile(@RequestPart("file") MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            String fileName = file.getOriginalFilename();
            long contentLength = file.getSize();
            s3UploadService.uploadFileToS3(inputStream, fileName, contentLength);
            return "업로드 성공";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "업로드 실패: " + e.getMessage();
        }
    }

    @PostMapping("/stream")
    public String uploadStream(
            @RequestBody InputStream inputStream,
            @RequestHeader("file-name") String fileName,
            @RequestHeader(value = "Content-Length", required = false) Long contentLength,
            HttpServletRequest request
    ) {
        try {
            log.info("요청 Content-Type: {}", request.getContentType());  // 진짜 들어온 값 확인
            long length = (contentLength != null && contentLength > 0)
                    ? contentLength
                    : inputStream.available();  // fallback
//            s3UploadService.uploadFileToS3(inputStream, fileName, length);
            return "업로드 성공";
        } catch (Exception e) {
            log.error("업로드 실패", e);
            return "업로드 실패: " + e.getMessage();
        }
    }

    record TestPreSignedUrlRequest(String fileName, String contentType, Long fileSize) {
    }
}
