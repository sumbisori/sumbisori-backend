package com.groom.sumbisori.domain.collection.error;

import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class SeafoodCollectionException extends BusinessException {
    public SeafoodCollectionException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
