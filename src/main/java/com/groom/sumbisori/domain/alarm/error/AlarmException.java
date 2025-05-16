package com.groom.sumbisori.domain.alarm.error;

import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class AlarmException extends BusinessException {
    public AlarmException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
