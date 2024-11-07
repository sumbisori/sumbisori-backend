package com.groom.sumbisori.domain.reservation.controller;

import static com.groom.sumbisori.domain.reservation.error.ReservationErrorCode.Const.RESERVATION_ALREADY_COMPLETED;
import static com.groom.sumbisori.domain.reservation.error.ReservationErrorCode.Const.RESERVATION_FORBIDDEN;
import static com.groom.sumbisori.domain.reservation.error.ReservationErrorCode.Const.RESERVATION_NOT_FOUND;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.common.springdoc.ApiExceptionExplanation;
import com.groom.sumbisori.common.springdoc.ApiResponseExplanations;
import com.groom.sumbisori.domain.reservation.dto.ReservationCount;
import com.groom.sumbisori.domain.reservation.dto.ReservationDto;
import com.groom.sumbisori.domain.reservation.dto.ReservationRequest;
import com.groom.sumbisori.domain.reservation.entity.Reservation.Status;
import com.groom.sumbisori.domain.reservation.error.ReservationErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "reservations", description = "예약 API")
public interface ReservationApi {

    @Operation(summary = "예약 목록 조회 - 인증")
    public ResponseEntity<List<ReservationDto>> getMyReservations(@LoginUser Long userId,
                                                                  @RequestParam Status status);

    @Operation(summary = "예약 목록 갯수 조회 - 인증")
    public ResponseEntity<ReservationCount> getMyReservationCount(@LoginUser Long userId);

    @Operation(summary = "예약 생성 - 인증")
    public ResponseEntity<Void> createReservation(@LoginUser Long userId,
                                                  @RequestBody ReservationRequest reservationRequest);

    @Operation(summary = "체험 완료 - 인증")
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(value = ReservationErrorCode.class, constant = RESERVATION_ALREADY_COMPLETED, name = "이미 체험이 완료됐을때"),
                    @ApiExceptionExplanation(value = ReservationErrorCode.class, constant = RESERVATION_FORBIDDEN, name = "예약에 대한 권한이 없을때"),
                    @ApiExceptionExplanation(value = ReservationErrorCode.class, constant = RESERVATION_NOT_FOUND, name = "없는 예약번호 일때"),
            }
    )
    public ResponseEntity<Void> completeReservation(@LoginUser Long userId, @PathVariable Long reservationId);

    @Operation(summary = "예약 취소 - 인증")
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(value = ReservationErrorCode.class, constant = RESERVATION_ALREADY_COMPLETED, name = "이미 체험이 완료됐을때"),
                    @ApiExceptionExplanation(value = ReservationErrorCode.class, constant = RESERVATION_FORBIDDEN, name = "예약에 대한 권한이 없을때"),
                    @ApiExceptionExplanation(value = ReservationErrorCode.class, constant = RESERVATION_NOT_FOUND, name = "없는 예약번호 일때"),
            }
    )
    public ResponseEntity<Void> cancelReservation(@LoginUser Long userId, @PathVariable Long reservationId);
}
