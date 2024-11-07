package com.groom.sumbisori.domain.reservation.error.exception;


import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class ReservationException extends BusinessException {
    public ReservationException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
