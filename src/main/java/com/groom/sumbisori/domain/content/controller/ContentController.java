package com.groom.sumbisori.domain.content.controller;

import com.groom.sumbisori.domain.content.dto.response.YoutubeResponse;
import com.groom.sumbisori.domain.content.service.YoutubeLookupService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentController implements ContentApi {
    private final YoutubeLookupService youtubeLookupService;

    @Override
    @GetMapping("/youtube")
    public ResponseEntity<Set<YoutubeResponse>> getYoutubeList(
            @RequestParam(required = false, defaultValue = "5") @Min(1) @Max(50) int count) {
        return ResponseEntity.ok(youtubeLookupService.lookup(count));
    }
}
