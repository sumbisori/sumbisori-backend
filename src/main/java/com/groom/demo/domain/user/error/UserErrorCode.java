package com.groom.demo.domain.user.error;

import com.groom.demo.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {
    NICKNAME_DUPLICATION(HttpStatus.BAD_REQUEST, "닉네임이 중복되었습니다."),
    USER_NOT_MATCHED(HttpStatus.BAD_REQUEST, "사용자 정보가 일치하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public static class Const {
        public static final String NICKNAME_DUPLICATION = "NICKNAME_DUPLICATION";
        public static final String USER_NOT_MATCHED = "USER_NOT_MATCHED";
        public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    }
}
