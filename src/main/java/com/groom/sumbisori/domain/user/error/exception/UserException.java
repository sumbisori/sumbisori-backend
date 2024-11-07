package com.groom.sumbisori.domain.user.error.exception;


import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class UserException extends BusinessException {
    public UserException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
