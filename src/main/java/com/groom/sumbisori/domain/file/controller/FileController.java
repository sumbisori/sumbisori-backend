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
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Slf4j
public class FileController implements FileApi {
    private final S3PreSignedUrlService s3PreSignedUrlService;
    private final ImageAnalyzeService imageAnalyzeService;
    private final FileLookupService fileLookupService;

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

    @GetMapping("/analyze")
    public ResponseEntity<List<SeafoodRecognitionResponse>> imageAnalyze(@RequestParam String imageIdentifier) {
        return ResponseEntity.ok(imageAnalyzeService.analyze(imageIdentifier));
    }
}
