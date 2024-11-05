package com.groom.demo.domain.seafood.controller;

import static com.groom.demo.domain.seafood.error.SeafoodErrorCode.Const.SEAFOOD_NOT_FOUND;

import com.groom.demo.common.springdoc.ApiExceptionExplanation;
import com.groom.demo.common.springdoc.ApiResponseExplanations;
import com.groom.demo.domain.collection.dto.response.MyCollectionSeafood;
import com.groom.demo.domain.seafood.dto.MySeafoodDto;
import com.groom.demo.domain.seafood.dto.SeafoodRequest;
import com.groom.demo.domain.seafood.dto.SeafoodResponse;
import com.groom.demo.domain.seafood.error.SeafoodErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "seafoods", description = "해산물 API")
public interface SeafoodApi {
    @Operation(summary = "해산물 종류 조회")
    ResponseEntity<List<SeafoodResponse>> getAllSeafoods();

    @Operation(summary = "해산물 수집 현황 조회 (인증)")
    ResponseEntity<List<MySeafoodDto>> getSeafoodList(Long userId);

    @Operation(summary = "나의 수집된 해산물 조회 (인증)")
    ResponseEntity<List<MyCollectionSeafood>> getMySeafoods(Long userId);

    @Operation(summary = "해산물 등록 (인증)")
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(value = SeafoodErrorCode.class, constant = SEAFOOD_NOT_FOUND, name = "해당 해산물 번호가 존재하지 않을 때"),
            }
    )
    ResponseEntity<Void> createSeafood(Long userId, SeafoodRequest request);
}
