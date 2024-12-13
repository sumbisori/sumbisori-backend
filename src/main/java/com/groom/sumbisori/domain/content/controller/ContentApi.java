package com.groom.sumbisori.domain.content.controller;

import com.groom.sumbisori.domain.content.dto.response.YoutubeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "contents", description = "콘텐츠 API")
public interface ContentApi {
    @Operation(summary = "유튜브 영상 목록 조회")
    ResponseEntity<List<YoutubeResponse>> getYoutubeList(
            @Parameter(description = "조회 갯수 (기본값: 5, 최소: 1, 최대값: 50)", example = "5")
            @RequestParam(required = false, defaultValue = "5") @Min(1) @Max(50) int count);
}
