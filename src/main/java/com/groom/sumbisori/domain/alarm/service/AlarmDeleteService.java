package com.groom.sumbisori.domain.alarm.service;

import static com.groom.sumbisori.domain.alarm.error.AlarmErrorCode.ALARM_NOT_FOUND;
import static com.groom.sumbisori.domain.alarm.error.AlarmErrorCode.ALARM_NOT_OWNED;

import com.groom.sumbisori.domain.alarm.entity.Alarm;
import com.groom.sumbisori.domain.alarm.error.AlarmException;
import com.groom.sumbisori.domain.alarm.repository.AlarmRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmDeleteService {
    private final AlarmRepository alarmRepository;

    public void delete(final Long userId, final Long alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(() -> new AlarmException(ALARM_NOT_FOUND));
        if (!alarm.isOwner(userId)) {
            throw new AlarmException(ALARM_NOT_OWNED);
        }
        alarm.delete();
    }
}
