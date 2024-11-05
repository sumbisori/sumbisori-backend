package com.groom.demo.domain.reservation.controller;

import com.groom.demo.domain.reservation.dto.ReservationCount;
import com.groom.demo.domain.reservation.dto.ReservationDto;
import com.groom.demo.domain.reservation.dto.ReservationRequest;
import com.groom.demo.domain.reservation.entity.Reservation.Status;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReservationApi {

    @Operation(summary = "예약 목록 조회 - 인증")
    public ResponseEntity<List<ReservationDto>> getMyReservations(@RequestHeader("userId") Long userId,
                                                                  @RequestParam Status status);

    @Operation(summary = "예약 목록 갯수 조회 - 인증")
    public ResponseEntity<ReservationCount> getMyReservationCount(@RequestHeader("userId") Long userId);

    @Operation(summary = "예약 생성")
    public ResponseEntity<Void> createReservation(@RequestHeader("userId") Long userId,
                                               @RequestBody ReservationRequest reservationRequest);
}
