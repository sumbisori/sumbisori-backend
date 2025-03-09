package com.groom.sumbisori.domain.file.controller;

import com.groom.sumbisori.domain.file.dto.PreSignedUrlRequest;
import com.groom.sumbisori.domain.file.dto.PreSignedUrlResponse;
import com.groom.sumbisori.domain.file.dto.SeafoodRecognitionResponse;
import com.groom.sumbisori.domain.file.service.ImageAnalyzeService;
import com.groom.sumbisori.domain.file.service.S3PreSignedUrlService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController implements FileApi {
    private final S3PreSignedUrlService s3PreSignedUrlService;
    private final ImageAnalyzeService imageAnalyzeService;

    @PostMapping("/presigned-url")
    public ResponseEntity<List<PreSignedUrlResponse>> createPreSignedUrl(
            @Valid @RequestBody PreSignedUrlRequest request) {
        return ResponseEntity.ok().body(s3PreSignedUrlService.create(request));
    }

    @GetMapping("/analyze")
    public ResponseEntity<List<SeafoodRecognitionResponse>> imageAnalyze(@RequestParam String key) {
        return ResponseEntity.ok(imageAnalyzeService.analyze(key));
    }
}
