package com.groom.sumbisori.domain.collectionitem.error;

import com.groom.sumbisori.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CollectionItemErrorcode implements ErrorCode {
    DUPLICATE_SEAFOOD(HttpStatus.BAD_REQUEST, "해산물이 중복되었습니다."),;

    private final HttpStatus httpStatus;
    private final String message;

    public static class Const {
        public static final String DUPLICATE_SEAFOOD = "DUPLICATE_SEAFOOD";
    }
}
