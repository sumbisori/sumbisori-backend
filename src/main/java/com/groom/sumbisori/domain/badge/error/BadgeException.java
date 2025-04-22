package com.groom.sumbisori.domain.badge.error;

import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class BadgeException extends BusinessException {
    public BadgeException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
