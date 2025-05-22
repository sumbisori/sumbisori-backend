package com.groom.sumbisori.domain.alarm.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.common.dto.PageResponse;
import com.groom.sumbisori.domain.alarm.dto.response.AlarmResponse;
import com.groom.sumbisori.domain.alarm.service.AlarmDeleteService;
import com.groom.sumbisori.domain.alarm.service.AlarmLookupService;
import com.groom.sumbisori.domain.alarm.service.AlarmReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alarms")
@RequiredArgsConstructor
public class AlarmController implements AlarmApi {
    private final AlarmLookupService alarmLookupService;
    private final AlarmReadService alarmReadService;
    private final AlarmDeleteService alarmDeleteService;

    @Override
    @GetMapping
    public ResponseEntity<PageResponse<AlarmResponse>> getAlarms(@LoginUser Long userId, Pageable pageable) {
        return ResponseEntity.ok(alarmLookupService.lookup(userId, pageable));
    }

    @Override
    @PostMapping("/{alarmId}")
    public ResponseEntity<Void> readAlarm(@LoginUser Long userId, @PathVariable Long alarmId) {
        alarmReadService.read(userId, alarmId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{alarmId}")
    public ResponseEntity<Void> deleteAlarm(@LoginUser Long userId, @PathVariable Long alarmId) {
        alarmDeleteService.delete(userId, alarmId);
        return ResponseEntity.noContent().build();
    }
}
