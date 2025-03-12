package com.groom.sumbisori.domain.seafood.controller;

import com.groom.sumbisori.domain.collection.dto.response.MyCollectionSeafood;
import com.groom.sumbisori.domain.seafood.dto.MySeafoodDto;
import com.groom.sumbisori.domain.seafood.dto.SeafoodResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "seafoods", description = "해산물 API")
public interface SeafoodApi {
    @Operation(summary = "해산물 종류 조회")
    ResponseEntity<List<SeafoodResponse>> getAllSeafoods();

    @Operation(summary = "해산물 수집 현황 조회")
    ResponseEntity<List<MySeafoodDto>> getSeafoodList(Long userId);

    @Operation(summary = "나의 수집된 해산물 조회")
    ResponseEntity<List<MyCollectionSeafood>> getMySeafoods(Long userId);
}
