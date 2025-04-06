package com.groom.sumbisori.domain.seafood.controller;

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

    @Override
    @GetMapping
    public ResponseEntity<List<SeafoodResponse>> getAllSeafoods() {
        return ResponseEntity.ok(seafoodService.getAllSeafoods());
    }
}
