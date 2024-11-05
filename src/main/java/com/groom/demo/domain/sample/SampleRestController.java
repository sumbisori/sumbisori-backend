package com.groom.demo.domain.sample;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
@Tag(name = "test", description = "Test API")
public class SampleRestController {
    private final RestTemplate restTemplate;

    @Operation(summary = "테스트 핑 API")
    @GetMapping("/test")
    public ResponseEntity<?> pingTest() {
        return ResponseEntity.ok(true);
    }

    @GetMapping("/kakao")
    public ResponseEntity<String> checkKakaoConnectivity() {
        String[] urls = {
                "https://kauth.kakao.com",
                "https://kapi.kakao.com"
        };

        StringBuilder responseBuilder = new StringBuilder();

        for (String url : urls) {
            try {
                restTemplate.getForObject(url, String.class);
                responseBuilder.append(url).append(": Connection Successful\n");
            } catch (Exception e) {
                responseBuilder.append(url).append(": Connection Failed - ").append(e.getMessage()).append("\n");
            }
        }

        return new ResponseEntity<>(responseBuilder.toString(), HttpStatus.OK);
    }
}
