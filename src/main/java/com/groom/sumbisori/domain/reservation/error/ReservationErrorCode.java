package com.groom.sumbisori.domain.reservation.error;

import com.groom.sumbisori.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReservationErrorCode implements ErrorCode {
    RESERVATION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "이미 체험이 완료됐습니다."),
    RESERVATION_FORBIDDEN(HttpStatus.FORBIDDEN, "예약에 대한 권한이 없습니다."),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "예약을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public static class Const {
        public static final String RESERVATION_ALREADY_COMPLETED = "RESERVATION_ALREADY_COMPLETED";
        public static final String RESERVATION_FORBIDDEN = "RESERVATION_FORBIDDEN";
        public static final String RESERVATION_NOT_FOUND = "RESERVATION_NOT_FOUND";
    }
}
