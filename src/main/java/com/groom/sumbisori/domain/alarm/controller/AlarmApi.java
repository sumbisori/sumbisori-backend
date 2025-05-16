package com.groom.sumbisori.domain.alarm.controller;

import com.groom.sumbisori.domain.alarm.dto.response.AlarmResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

public interface AlarmApi {
    /**
     * 알림 조회 API
     */
    @Operation(summary = "알림 조회")
    ResponseEntity<List<AlarmResponse>> getAlarms(Long userId, @PageableDefault Pageable pageable);

    /**
     * 알림 읽음 API
     */
    @Operation(summary = "알림 읽음 처리")
    ResponseEntity<Void> readAlarm(Long userId, Long alarmId);

    /**
     * 알림 삭제 API
     */
    @Operation(summary = "알림 삭제")
    ResponseEntity<Void> deleteAlarm(Long userId, Long alarmId);
}
