package com.groom.sumbisori.domain.seafood.error.exception;


import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class SeafoodException extends BusinessException {
    public SeafoodException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
