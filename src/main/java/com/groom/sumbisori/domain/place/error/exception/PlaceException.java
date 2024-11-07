package com.groom.sumbisori.domain.place.error.exception;


import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class PlaceException extends BusinessException {
    public PlaceException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
