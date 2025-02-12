package com.groom.sumbisori.domain.place.controller;

import com.groom.sumbisori.domain.place.dto.PlaceLocationResponse;
import com.groom.sumbisori.domain.place.dto.PlaceResponse;
import com.groom.sumbisori.domain.place.service.PlaceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController implements PlaceApi {
    private final PlaceService placeService;

    @Override
    @GetMapping
    public ResponseEntity<List<PlaceResponse>> getAllPlaces() {
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @Override
    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceResponse> getPlaceInfo(@PathVariable Long placeId) {
        return ResponseEntity.ok(placeService.getPlaceById(placeId));
    }

    @Override
    @GetMapping("/locations")
    public ResponseEntity<List<PlaceLocationResponse>> getPlaceLocations() {
        return ResponseEntity.ok(placeService.getAllPlaceLocations());
    }
}
