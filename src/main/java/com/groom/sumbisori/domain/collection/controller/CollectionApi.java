package com.groom.sumbisori.domain.collection.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.domain.collection.dto.response.MySeafoodCollection;
import com.groom.sumbisori.domain.collection.dto.response.MySeafoodCollectionStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "collections", description = "수집 API")
public interface CollectionApi {

    @Operation(summary = "내 해산물 수집 조회")
    ResponseEntity<List<MySeafoodCollection>> getMySeafoodCollection(@LoginUser Long userId);

    @Operation(summary = "해산물 수집 현황 조회")
    ResponseEntity<List<MySeafoodCollectionStatus>> getSeafoodCollectionStatus(@LoginUser Long userId);

}
