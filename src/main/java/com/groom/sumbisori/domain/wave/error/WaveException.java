package com.groom.sumbisori.domain.wave.error;

import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class WaveException extends BusinessException {
    public WaveException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
