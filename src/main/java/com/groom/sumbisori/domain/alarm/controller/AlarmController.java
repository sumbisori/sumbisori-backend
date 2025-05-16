package com.groom.sumbisori.domain.alarm.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.domain.alarm.dto.response.AlarmResponse;
import com.groom.sumbisori.domain.alarm.service.AlarmLookupService;
import com.groom.sumbisori.domain.alarm.service.AlarmReadService;
import java.util.List;
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

    @Override
    @GetMapping
    public ResponseEntity<List<AlarmResponse>> getAlarms(@LoginUser Long userId, Pageable pageable) {
        return ResponseEntity.ok(alarmLookupService.lookup(userId));
    }

    @Override
    @PostMapping("/{alarmId}")
    public ResponseEntity<Void> readAlarm(@LoginUser Long userId, @PathVariable Long alarmId) {
        alarmReadService.read(1L, alarmId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{alarmId}")
    public ResponseEntity<Void> deleteAlarm(Long userId, @PathVariable Long alarmId) {
        return null;
    }
}
