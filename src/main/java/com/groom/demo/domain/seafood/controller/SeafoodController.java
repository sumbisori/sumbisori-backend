package com.groom.demo.domain.seafood.controller;

import com.groom.demo.domain.collection.dto.response.MyCollectionSeafood;
import com.groom.demo.domain.collection.service.SeafoodCollectionService;
import com.groom.demo.domain.seafood.service.SeafoodService;
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
    private final SeafoodCollectionService seafoodCollectionService;

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
    public ResponseEntity<List<MyCollectionSeafood>> getMySeafoods(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(seafoodCollectionService.mySeafoodCollection(userId));
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> createSeafood(@RequestHeader("userId") Long userId,
                                              @RequestBody SeafoodRequest request) {
        seafoodCollectionService.createSeafoodCollection(userId, request);
        return ResponseEntity.ok().build();
    }
}
