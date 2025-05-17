package com.groom.sumbisori.domain.alarm.service;

import com.groom.sumbisori.domain.alarm.context.BadgeAlarmContext;
import com.groom.sumbisori.domain.alarm.context.ExperienceAlarmContext;
import com.groom.sumbisori.domain.alarm.entity.AlarmType;
import com.groom.sumbisori.domain.badge.dto.event.BadgeIssuedEvent;
import com.groom.sumbisori.domain.experience.dto.event.ExperienceCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlarmEventHandler {
    private final AlarmCreateService alarmService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(ExperienceCreateEvent event) {
        log.info("체험 생성 알림 발행 - 사용자 ID: {}, 체험 ID: {}", event.userId(), event.experienceId());
        alarmService.create(
                event.userId(),
                AlarmType.EXPERIENCE_COMPLETED,
                ExperienceAlarmContext.of(event.experienceId())
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(BadgeIssuedEvent event) {
        log.info("배지 발급 알림 발행 - 사용자 ID: {}, 배지 ID: {}", event.userId(), event.badgeId());
        alarmService.create(
                event.userId(),
                AlarmType.BADGE_ACQUIRED,
                BadgeAlarmContext.of(event.badgeId(), event.type(), event.name(), event.level())
        );
    }
}
