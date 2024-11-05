package com.groom.demo.domain.place.error.exception;


import com.groom.demo.common.error.ErrorCode;
import com.groom.demo.common.error.exception.BusinessException;

public class PlaceException extends BusinessException {
    public PlaceException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
