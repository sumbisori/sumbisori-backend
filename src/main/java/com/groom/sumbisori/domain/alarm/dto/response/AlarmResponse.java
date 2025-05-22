package com.groom.sumbisori.domain.alarm.dto.response;

import com.groom.sumbisori.domain.alarm.entity.AlarmType;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlarmResponse(
        Long alarmId,
        AlarmType type,
        String content,
        String link,
        Boolean isRead,
        LocalDate createdAt
) {
    public AlarmResponse(Long alarmId, AlarmType type, String content, String link, Boolean isRead, LocalDateTime createdAtTime) {
        this(alarmId, type, content, link, isRead, createdAtTime.toLocalDate());
    }
}
