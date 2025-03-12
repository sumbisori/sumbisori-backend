package com.groom.sumbisori.domain.file.service;

import com.groom.sumbisori.domain.file.dto.SeafoodRecognitionResponse;
import java.net.URL;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageAnalyzeService {
    private final S3PreSignedUrlService s3PreSignedUrlService;
    private final ChatGPTService chatGPTService;

    public List<SeafoodRecognitionResponse> analyze(String imageIdentifier) {
        URL preSignedGetUrl = s3PreSignedUrlService.getPreSignedGetUrl(imageIdentifier);
        return chatGPTService.analyzeSeafoodImage(preSignedGetUrl);
    }
}
