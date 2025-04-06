package com.groom.sumbisori.domain.collection.error;

import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class CollectionException extends BusinessException {
    public CollectionException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
