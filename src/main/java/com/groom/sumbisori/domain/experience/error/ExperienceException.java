package com.groom.sumbisori.domain.experience.error;

import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class ExperienceException extends BusinessException {
    public ExperienceException(final ErrorCode errorCode) {
        super(errorCode);
    }
}

