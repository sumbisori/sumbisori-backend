package com.groom.sumbisori.domain.alarm.controller;

import com.groom.sumbisori.common.dto.PageResponse;
import com.groom.sumbisori.common.springdoc.ApiExceptionExplanation;
import com.groom.sumbisori.common.springdoc.ApiResponseExplanations;
import com.groom.sumbisori.domain.alarm.dto.response.AlarmResponse;
import com.groom.sumbisori.domain.alarm.error.AlarmErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

public interface AlarmApi {
    /**
     * 알림 조회 API
     */
    @Operation(summary = "알림 조회")
    ResponseEntity<PageResponse<AlarmResponse>> getAlarms(Long userId, @PageableDefault Pageable pageable);

    /**
     * 알림 읽음 API
     */
    @Operation(summary = "알림 읽음 처리")
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(value = AlarmErrorCode.class, constant = AlarmErrorCode.Const.ALARM_NOT_FOUND, name = "해당 알람을 찾을 수 없습니다."),
                    @ApiExceptionExplanation(value = AlarmErrorCode.class, constant = AlarmErrorCode.Const.ALARM_NOT_OWNED, name = "사용자가 해당 알람을 소유하지 않았습니다.")
            }
    )
    ResponseEntity<Void> readAlarm(Long userId, Long alarmId);

    /**
     * 알림 삭제 API
     */
    @Operation(summary = "알림 삭제")
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(value = AlarmErrorCode.class, constant = AlarmErrorCode.Const.ALARM_NOT_FOUND, name = "해당 알람을 찾을 수 없습니다."),
                    @ApiExceptionExplanation(value = AlarmErrorCode.class, constant = AlarmErrorCode.Const.ALARM_NOT_OWNED, name = "사용자가 해당 알람을 소유하지 않았습니다.")
            }
    )
    ResponseEntity<Void> deleteAlarm(Long userId, Long alarmId);
}
