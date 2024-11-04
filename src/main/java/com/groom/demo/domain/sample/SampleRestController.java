package com.groom.demo.domain.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SampleRestController {
    @GetMapping("/test")
    public ResponseEntity<?> pingTest() {
        return ResponseEntity.ok(true);
    }
}
