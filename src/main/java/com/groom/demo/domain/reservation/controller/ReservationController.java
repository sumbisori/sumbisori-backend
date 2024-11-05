package com.groom.demo.domain.reservation.controller;

import com.groom.demo.domain.reservation.service.ReservationService;
import com.groom.demo.domain.reservation.entity.Status;
import com.groom.demo.domain.reservation.dto.ReservationCount;
import com.groom.demo.domain.reservation.dto.ReservationDto;
import com.groom.demo.domain.reservation.dto.ReservationRequest;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Operation(summary = "예약 목록 조회 - 인증")
    @GetMapping("/my")
    public ResponseEntity<List<ReservationDto>> getMyReservations(@RequestHeader("userId") Long userId,
                                                                  @RequestParam Status status) {
        return ResponseEntity.ok(reservationService.getMyReservations(userId, status));
    }

    @Operation(summary = "예약 목록 갯수 조회 - 인증")
    @GetMapping("/my/counts")
    public ResponseEntity<ReservationCount> getMyReservationCount(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(reservationService.getMyReservationCount(userId));
    }

    @Operation(summary = "예약 생성")
    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestHeader("userId") Long userId,
                                               @RequestBody ReservationRequest reservationRequest) {
        reservationService.createReservation(userId, reservationRequest);
        return ResponseEntity.ok().build();
    }
}
