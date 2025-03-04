package com.groom.sumbisori.domain.file.error;

import com.groom.sumbisori.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FileErrorcode implements ErrorCode {
    INVALID_IMAGE_CONTENT_TYPE(HttpStatus.BAD_REQUEST, "허용되지 않은 이미지 파일 형식입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public static class Const {
        public static final String INVALID_IMAGE_CONTENT_TYPE = "INVALID_IMAGE_CONTENT_TYPE";
    }
}
