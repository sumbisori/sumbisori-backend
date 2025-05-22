package com.groom.sumbisori.domain.alarm.service;

import com.groom.sumbisori.domain.alarm.context.BadgeAlarmContext;
import com.groom.sumbisori.domain.alarm.context.ExperienceAlarmContext;
import com.groom.sumbisori.domain.alarm.entity.AlarmType;
import com.groom.sumbisori.domain.badge.dto.event.BadgeIssuedEvent;
import com.groom.sumbisori.domain.experience.dto.event.ExperienceCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class AlarmEventHandler {
    private final AlarmCreateService alarmService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handle(ExperienceCreateEvent event) {
        alarmService.create(
                event.userId(),
                AlarmType.EXPERIENCE_COMPLETED,
                ExperienceAlarmContext.of(event.experienceId())
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handle(BadgeIssuedEvent event) {
        alarmService.create(
                event.userId(),
                AlarmType.BADGE_ACQUIRED,
                BadgeAlarmContext.of(event.badgeId(), event.type(), event.name(), event.level())
        );
    }
}
