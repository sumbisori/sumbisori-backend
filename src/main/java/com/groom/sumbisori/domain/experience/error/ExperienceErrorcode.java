package com.groom.sumbisori.domain.experience.error;

import com.groom.sumbisori.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExperienceErrorcode implements ErrorCode {
    EXPERIENCE_DATE_INVALID(HttpStatus.BAD_REQUEST, "유효하지 않은 경험 날짜입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public static class Const {
        public static final String EXPERIENCE_DATE_INVALID = "EXPERIENCE_DATE_INVALID";
    }

}

