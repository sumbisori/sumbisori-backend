package com.groom.sumbisori.domain.alarm.error;

import com.groom.sumbisori.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AlarmErrorCode implements ErrorCode {
    ALARM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 알람을 찾을 수 없습니다."),
    ALARM_NOT_OWNED(HttpStatus.FORBIDDEN, "사용자가 해당 알람을 소유하지 않았습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public static class Const {
        public static final String ALARM_NOT_FOUND = "ALARM_NOT_FOUND";
        public static final String ALARM_NOT_OWNED = "ALARM_NOT_OWNED";
    }
}
