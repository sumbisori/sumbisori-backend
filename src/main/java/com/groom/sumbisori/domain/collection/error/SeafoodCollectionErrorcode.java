package com.groom.sumbisori.domain.collection.error;

import com.groom.sumbisori.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SeafoodCollectionErrorcode implements ErrorCode {
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
