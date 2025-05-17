package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.entity.BadgeCode;
import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.groom.sumbisori.domain.badge.error.BadgeErrorcode;
import com.groom.sumbisori.domain.badge.error.BadgeException;
import com.groom.sumbisori.domain.badge.repository.BadgeLevelQueryRepository;
import com.groom.sumbisori.domain.collection.dto.event.CollectionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class FirstCollectionBadgeService {
    private final BadgeCreateService badgeCreateService;
    private final BadgeGrantValidator badgeGrantValidator;
    private final BadgeLevelQueryRepository badgeLevelQueryRepository;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(CollectionEvent event) {
        BadgeLevel badgeLevel = badgeLevelQueryRepository.findByCodeAndLevelFetchBadge(BadgeCode.FIRST_COLLECTION,
                        1)
                .orElseThrow(() -> new BadgeException(BadgeErrorcode.BADGE_NOT_FOUND));
        if (badgeGrantValidator.isGrantable(event.userId(), badgeLevel)) {
            badgeCreateService.create(event.userId(), badgeLevel);
        }
    }
}
