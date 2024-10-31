package com.groom.demo.domain.seafood;

import com.groom.demo.domain.seafood.dto.MySeafoodDto;
import com.groom.demo.domain.seafood.dto.SeafoodRequest;
import com.groom.demo.domain.seafood.dto.SeafoodResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seafoods")
@RequiredArgsConstructor
public class SeafoodController {
    private final SeafoodService seafoodService;

    @Operation(description = "해산물 목록 조회")
    @GetMapping
    public ResponseEntity<List<SeafoodResponse>> getAllSeafoods() {
        List<SeafoodResponse> collect = Arrays.stream(Seafood.values())
                .map(seafood -> new SeafoodResponse(seafood.name(), seafood.getName(), seafood.getEnglishName(),
                        seafood.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    @Operation(description = "내 해산물 목록 조회 + 인증")
    @GetMapping("/my")
    public ResponseEntity<List<MySeafoodDto>> getMySeafoodList(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(seafoodService.mySeafoodList(userId));
    }

    @Operation(description = "해산물 등록 + 인증")
    @PostMapping
    public ResponseEntity<Void> createSeafood(@RequestHeader("userId") Long userId, @RequestBody SeafoodRequest request) {
        seafoodService.createSeafood(userId, request);
        return ResponseEntity.ok().build();
    }
}
