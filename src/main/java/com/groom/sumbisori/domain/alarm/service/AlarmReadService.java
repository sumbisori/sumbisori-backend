package com.groom.sumbisori.domain.alarm.service;

import static com.groom.sumbisori.domain.alarm.error.AlarmErrorCode.ALARM_NOT_FOUND;
import static com.groom.sumbisori.domain.alarm.error.AlarmErrorCode.ALARM_NOT_OWNED;

import com.groom.sumbisori.domain.alarm.entity.Alarm;
import com.groom.sumbisori.domain.alarm.error.AlarmException;
import com.groom.sumbisori.domain.alarm.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmReadService {
    private final AlarmRepository alarmRepository;

    /**
     * 알림 읽기
     */
    public void read(final Long userId, final Long alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(() -> new AlarmException(ALARM_NOT_FOUND));
        if (!alarm.isOwner(userId)) {
            throw new AlarmException(ALARM_NOT_OWNED);
        }
        alarm.read();
    }
}
