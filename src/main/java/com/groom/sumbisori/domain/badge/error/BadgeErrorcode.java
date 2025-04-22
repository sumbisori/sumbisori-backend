package com.groom.sumbisori.domain.badge.error;

import com.groom.sumbisori.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BadgeErrorcode implements ErrorCode {
    BADGE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 뱃지를 찾을 수 없습니다."),
    BADGE_LEVEL_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 뱃지레벨를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
