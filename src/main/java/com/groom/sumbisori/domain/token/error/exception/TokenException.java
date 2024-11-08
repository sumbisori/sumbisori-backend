package com.groom.sumbisori.domain.token.error.exception;

import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class TokenException extends BusinessException {
    public TokenException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
