package com.groom.sumbisori.domain.seafood.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.domain.collection.dto.response.MyCollectionSeafood;
import com.groom.sumbisori.domain.collection.service.SeafoodCollectionService;
import com.groom.sumbisori.domain.seafood.dto.MySeafoodDto;
import com.groom.sumbisori.domain.seafood.dto.SeafoodResponse;
import com.groom.sumbisori.domain.seafood.service.SeafoodService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<MySeafoodDto>> getSeafoodList(@LoginUser Long userId) {
        return ResponseEntity.ok(seafoodService.mySeafoodList(userId));
    }

    @Override
    @GetMapping("/types")
    public ResponseEntity<List<SeafoodResponse>> getAllSeafoods() {
        return ResponseEntity.ok(seafoodService.getAllSeafoods());
    }

    @Override
    @GetMapping("/collected")
    public ResponseEntity<List<MyCollectionSeafood>> getMySeafoods(@LoginUser Long userId) {
        return ResponseEntity.ok(seafoodCollectionService.mySeafoodCollection(userId));
    }
}
