package com.groom.demo.domain.user.error.exception;


import com.groom.demo.common.error.ErrorCode;
import com.groom.demo.common.error.exception.BusinessException;

public class UserException extends BusinessException {
    public UserException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
