package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.dto.event.SignUpEvent;
import com.groom.sumbisori.domain.badge.entity.Badge;
import com.groom.sumbisori.domain.badge.entity.BadgeType;
import com.groom.sumbisori.domain.badge.repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
@Transactional
public class FirstLoginBadgeService {
    private final BadgeRepository badgeRepository;

    /**
     * 첫 로그인 시 뱃지를 부여
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void awardFirstJoinBadge(SignUpEvent event) {
        Badge badge = Badge.builder()
                .userId(event.userId())
                .type(BadgeType.FIRST_LOGIN)
                .build();
        badgeRepository.save(badge);
    }
}
