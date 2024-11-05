package com.groom.demo.domain.place.controller;

import com.groom.demo.domain.place.dto.PlaceResponse;
import com.groom.demo.domain.place.service.PlaceService;
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
    @GetMapping("/place/{placeId}")
    public ResponseEntity<PlaceResponse> getPlaceInfo(@PathVariable String placeValue) {
//        Place place = Place.valueOf(placeValue);
//        PlaceListDto placeListDto = new PlaceListDto(
//                place.name(),
//                place.getName(),
//                place.getAddress(),
//                place.getPrice(),
//                place.getDescription(),
//                place.getImageUrl(),
//                place.getAvailableDate(),
//                place.getX(),
//                place.getY()
//        );
        return ResponseEntity.ok().build();
    }
}
