package com.groom.sumbisori.domain.seafood.controller;

import com.groom.sumbisori.domain.seafood.dto.SeafoodResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "seafoods", description = "해산물 API")
public interface SeafoodApi {
    @Operation(summary = "해산물 종류 조회")
    ResponseEntity<List<SeafoodResponse>> getAllSeafoods();
}
