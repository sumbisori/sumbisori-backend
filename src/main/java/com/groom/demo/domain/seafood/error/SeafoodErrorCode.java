package com.groom.demo.domain.seafood.error;

import com.groom.demo.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SeafoodErrorCode implements ErrorCode {
    SEAFOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "해산물을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public static class Const {
        public static final String SEAFOOD_NOT_FOUND = "SEAFOOD_NOT_FOUND";
    }
}
