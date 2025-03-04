package com.groom.sumbisori.domain.file.error;

import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class FileException extends BusinessException {
    public FileException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
