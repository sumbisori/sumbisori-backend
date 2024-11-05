package com.groom.demo.domain.place.controller;

import com.groom.demo.domain.place.dto.PlaceMapResponse;
import com.groom.demo.domain.place.dto.PlaceResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface PlaceApi {
    @Operation(summary = "해녀체험 장소 목록 조회")
    public ResponseEntity<List<PlaceMapResponse>> getAllPlaces();

    @Operation(summary = "해녀체험 장소 상세 조회")
    public ResponseEntity<PlaceResponse> getPlaceInfo(@PathVariable Long placeId);
}
