package com.groom.demo.domain.reservation;

import com.groom.demo.domain.place.Place;
import com.groom.demo.domain.place.PlaceListDto;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/haenyeo-place")
    public ResponseEntity<?> getAllPlaces() {
        List<PlaceListDto> list = Arrays.stream(Place.values())
                .map(place -> new PlaceListDto(
                        place.name(),
                        place.getName(),
                        place.getAddress(),
                        place.getPrice(),
                        place.getDescription(),
                        place.getImageUrl(),
                        place.getAvailableDate()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/haenyeo-place/{placeValue}")
    public ResponseEntity<?> getPlaceInfo(@PathVariable String placeValue) {
        Place place = Place.valueOf(placeValue);
        PlaceListDto placeListDto = new PlaceListDto(
                place.name(),
                place.getName(),
                place.getAddress(),
                place.getPrice(),
                place.getDescription(),
                place.getImageUrl(),
                place.getAvailableDate()
        );
        return ResponseEntity.ok(placeListDto);
    }
}
