package com.groom.demo.domain.reservation.controller;

import com.groom.demo.domain.reservation.dto.ReservationCount;
import com.groom.demo.domain.reservation.dto.ReservationDto;
import com.groom.demo.domain.reservation.dto.ReservationRequest;
import com.groom.demo.domain.reservation.entity.Reservation.Status;
import com.groom.demo.domain.reservation.service.ReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationController implements ReservationApi {
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getMyReservations(@RequestHeader("userId") Long userId,
                                                                  @RequestParam Status status) {
        return ResponseEntity.ok(reservationService.getMyReservations(userId, status));
    }

    @GetMapping("/count")
    public ResponseEntity<ReservationCount> getMyReservationCount(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(reservationService.getMyReservationCount(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestHeader("userId") Long userId,
                                               @RequestBody ReservationRequest reservationRequest) {
        reservationService.createReservation(userId, reservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
