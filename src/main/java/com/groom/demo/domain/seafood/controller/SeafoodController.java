package com.groom.demo.domain.seafood.controller;

import com.groom.demo.domain.seafood.SeafoodService;
import com.groom.demo.domain.seafood.dto.MySeafoodDto;
import com.groom.demo.domain.seafood.dto.SeafoodRequest;
import com.groom.demo.domain.seafood.dto.SeafoodResponse;
import java.util.List;
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
public class SeafoodController implements SeafoodApi {
    private final SeafoodService seafoodService;

    @Override
    @GetMapping
    public ResponseEntity<List<MySeafoodDto>> getSeafoodList(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(seafoodService.mySeafoodList(userId));
    }

    @Override
    @GetMapping("/types")
    public ResponseEntity<List<SeafoodResponse>> getAllSeafoods() {
        return ResponseEntity.ok(seafoodService.getAllSeafoods());
    }

    @Override
    @GetMapping("/collected")
    public ResponseEntity<?> getMySeafoods(Long userId) {
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> createSeafood(@RequestHeader("userId") Long userId,
                                              @RequestBody SeafoodRequest request) {
        seafoodService.createSeafood(userId, request);
        return ResponseEntity.ok().build();
    }
}