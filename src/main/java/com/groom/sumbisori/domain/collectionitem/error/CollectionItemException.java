package com.groom.sumbisori.domain.collectionitem.error;

import com.groom.sumbisori.common.error.ErrorCode;
import com.groom.sumbisori.common.error.exception.BusinessException;

public class CollectionItemException extends BusinessException {
    public CollectionItemException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
