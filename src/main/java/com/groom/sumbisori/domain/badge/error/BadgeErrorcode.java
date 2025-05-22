package com.groom.sumbisori.domain.badge.error;

import com.groom.sumbisori.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BadgeErrorcode implements ErrorCode {
    BADGE_NOT_OWNED(HttpStatus.FORBIDDEN, "사용자가 해당 배지를 소유하지 않았습니다."),
    BADGE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 배지를 찾을 수 없습니다."),
    BADGE_LEVEL_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 배지 레벨를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public static class Const {
        public static final String BADGE_NOT_OWNED = "BADGE_NOT_OWNED";
        public static final String BADGE_NOT_FOUND = "BADGE_NOT_FOUND";
        public static final String BADGE_LEVEL_NOT_FOUND = "BADGE_LEVEL_NOT_FOUND";
    }
}
