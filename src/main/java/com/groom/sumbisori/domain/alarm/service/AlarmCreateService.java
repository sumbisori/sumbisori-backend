package com.groom.sumbisori.domain.alarm.service;

import com.groom.sumbisori.domain.alarm.context.AlarmContext;
import com.groom.sumbisori.domain.alarm.dto.common.AlarmContent;
import com.groom.sumbisori.domain.alarm.entity.Alarm;
import com.groom.sumbisori.domain.alarm.entity.AlarmType;
import com.groom.sumbisori.domain.alarm.registry.AlarmContentBuilderRegistry;
import com.groom.sumbisori.domain.alarm.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AlarmCreateService {
    private final AlarmRepository alarmRepository;
    private final AlarmContentBuilderRegistry alarmContentBuilderRegistry;

    public void create(Long userId, AlarmType type, AlarmContext context) {
        AlarmContent content = alarmContentBuilderRegistry.get(type).build(context);
        Alarm alarm = Alarm.create(userId, type, content);
        alarmRepository.save(alarm);
        log.info("알림 생성 성공: userId={}, type={}", userId, type);
        // alarmWebSocketService.sendAlarm(userId, alarm); // 실시간 전송
    }
}
