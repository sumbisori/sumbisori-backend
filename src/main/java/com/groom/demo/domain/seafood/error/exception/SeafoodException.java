package com.groom.demo.domain.seafood.error.exception;


import com.groom.demo.common.error.ErrorCode;
import com.groom.demo.common.error.exception.BusinessException;

public class SeafoodException extends BusinessException {
    public SeafoodException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
