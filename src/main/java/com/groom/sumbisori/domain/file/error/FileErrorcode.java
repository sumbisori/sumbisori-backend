package com.groom.sumbisori.domain.file.error;

import com.groom.sumbisori.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FileErrorcode implements ErrorCode {
    INVALID_IMAGE_CONTENT_TYPE(HttpStatus.BAD_REQUEST, "허용되지 않은 이미지 파일 형식입니다."),
    INVALID_FILE(HttpStatus.BAD_REQUEST, "유효하지 않은 파일입니다."),
    INVALID_SEQUENCE(HttpStatus.BAD_REQUEST, "유효하지 않은 순서입니다."),
    INVALID_PERMISSION(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "파일을 찾을 수 없습니다."),
    CHAT_GPT_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "Chat GPT 서비스 오류입니다."),
    S3_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "S3 서비스 오류입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public static class Const {
        public static final String INVALID_IMAGE_CONTENT_TYPE = "INVALID_IMAGE_CONTENT_TYPE";
        public static final String INVALID_FILE = "INVALID_FILE";
        public static final String INVALID_SEQUENCE = "INVALID_SEQUENCE";
        public static final String INVALID_PERMISSION = "INVALID_PERMISSION";
        public static final String FILE_NOT_FOUND = "FILE_NOT_FOUND";
        public static final String S3_ERROR = "S3_ERROR";
        public static final String CHAT_GPT_ERROR = "CHAT_GPT_ERROR";
    }
}
