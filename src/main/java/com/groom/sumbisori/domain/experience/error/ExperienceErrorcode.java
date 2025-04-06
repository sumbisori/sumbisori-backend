package com.groom.sumbisori.domain.experience.error;

import com.groom.sumbisori.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExperienceErrorcode implements ErrorCode {
    EXPERIENCE_DATE_INVALID(HttpStatus.BAD_REQUEST, "유효하지 않은 체 날짜입니다."),
    EXPERIENCE_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "체험에 대한 권한이 없습니다."),
    EXPERIENCE_NOT_FOUND(HttpStatus.NOT_FOUND, "체험을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public static class Const {
        public static final String EXPERIENCE_DATE_INVALID = "EXPERIENCE_DATE_INVALID";
        public static final String EXPERIENCE_PERMISSION_DENIED = "EXPERIENCE_PERMISSION_DENIED";
        public static final String EXPERIENCE_NOT_FOUND = "EXPERIENCE_NOT_FOUND";
    }

}

