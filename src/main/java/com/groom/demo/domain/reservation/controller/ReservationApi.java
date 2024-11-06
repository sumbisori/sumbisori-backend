package com.groom.demo.domain.reservation.controller;

import com.groom.demo.common.config.LoginUser;
import com.groom.demo.domain.reservation.dto.ReservationCount;
import com.groom.demo.domain.reservation.dto.ReservationDto;
import com.groom.demo.domain.reservation.dto.ReservationRequest;
import com.groom.demo.domain.reservation.entity.Reservation.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "reservations", description = "예약 API")
public interface ReservationApi {

    @Operation(summary = "예약 목록 조회 - 인증")
    public ResponseEntity<List<ReservationDto>> getMyReservations(@LoginUser Long userId,
                                                                  @RequestParam Status status);

    @Operation(summary = "예약 목록 갯수 조회 - 인증")
    public ResponseEntity<ReservationCount> getMyReservationCount(@LoginUser Long userId);

    @Operation(summary = "예약 생성")
    public ResponseEntity<Void> createReservation(@LoginUser Long userId,
                                                  @RequestBody ReservationRequest reservationRequest);
}
