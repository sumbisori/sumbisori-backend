package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.dto.event.SignUpEvent;
import com.groom.sumbisori.domain.badge.entity.BadgeCode;
import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.groom.sumbisori.domain.badge.error.BadgeErrorcode;
import com.groom.sumbisori.domain.badge.error.BadgeException;
import com.groom.sumbisori.domain.badge.repository.BadgeLevelQueryRepository;
import com.groom.sumbisori.domain.user.service.UserBadgeUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FirstLoginBadgeService {
    private final UserBadgeUpdateService userBadgeUpdateService;
    private final BadgeCreateService badgeCreateService;
    private final BadgeLevelQueryRepository badgeLevelQueryRepository;

    /**
     * 첫 로그인 시 뱃지를 부여
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void awardFirstJoinBadge(SignUpEvent event) {
        BadgeLevel badgeLevel = badgeLevelQueryRepository.findByCodeAndLevelFetchBadge(BadgeCode.FIRST_JOIN, 1)
                .orElseThrow(() -> new BadgeException(BadgeErrorcode.BADGE_NOT_FOUND));
        badgeCreateService.create(event.userId(), badgeLevel);
        userBadgeUpdateService.updateUserBadgeLevel(event.userId(), badgeLevel.getId());
        log.info("첫 로그인 뱃지 부여 - 사용자 ID: {}", event.userId());
    }
}
