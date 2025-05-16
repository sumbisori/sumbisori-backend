package com.groom.sumbisori.domain.alarm.service;

import com.groom.sumbisori.domain.alarm.dto.response.AlarmResponse;
import com.groom.sumbisori.domain.alarm.repository.AlarmQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmLookupService {
    private final AlarmQueryRepository alarmQueryRepository;

    /**
     * 사용자의 알림 조회
     */
    public List<AlarmResponse> lookup(final Long userId) {
        return alarmQueryRepository.findAlarmsByUserId(userId);
    }
}
