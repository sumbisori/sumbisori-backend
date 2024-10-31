package com.groom.demo.domain.reservation;

import com.groom.demo.domain.place.Place;
import com.groom.demo.domain.place.PlaceListDto;
import com.groom.demo.domain.reservation.dto.ReservationDto;
import com.groom.demo.domain.reservation.dto.ReservationRequest;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Operation(summary = "예약 목록 조회")
    @GetMapping("/my")
    public ResponseEntity<List<ReservationDto>> getMyReservations(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(reservationService.getMyReservations(userId));
    }

    @Operation(summary = "해녀체험장 목록 조회")
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
                        place.getAvailableDate(),
                        place.getX(),
                        place.getY()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "해녀체험장 상세 조회")
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
                place.getAvailableDate(),
                place.getX(),
                place.getY()
        );
        return ResponseEntity.ok(placeListDto);
    }

    @Operation(summary = "예약 생성")
    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestHeader("userId") Long userId,
                                               @RequestBody ReservationRequest reservationRequest) {
        reservationService.createReservation(userId, reservationRequest);
        return ResponseEntity.ok().build();
    }
}
