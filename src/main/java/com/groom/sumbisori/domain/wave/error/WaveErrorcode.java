package com.groom.sumbisori.domain.wave.error;

import com.groom.sumbisori.common.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum WaveErrorcode implements ErrorCode {
    WAVE_DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 지역의 파고 정보가 제공되지 않습니다."),
    EXTERNAL_API_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "파고 API 호출 중 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
