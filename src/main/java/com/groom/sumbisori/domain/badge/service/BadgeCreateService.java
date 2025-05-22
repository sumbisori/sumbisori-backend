package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.dto.event.BadgeIssuedEvent;
import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.groom.sumbisori.domain.badge.entity.UserBadge;
import com.groom.sumbisori.domain.badge.repository.UserBadgeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BadgeCreateService {
    private final UserBadgeRepository userBadgeRepository;
    private final ApplicationEventPublisher eventPublisher;

    public void create(Long userId, BadgeLevel badgeLevel) {
        userBadgeRepository.save(UserBadge.create(userId, badgeLevel));
        eventPublisher.publishEvent(BadgeIssuedEvent.of(userId, badgeLevel.getBadge(), badgeLevel));
        log.info("배지 부여 완료 : userId = {}, badgeLevel = {}", userId, badgeLevel.getId());
    }
}
