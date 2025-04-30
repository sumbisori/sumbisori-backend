package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.entity.Badge;
import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.groom.sumbisori.domain.badge.entity.SeafoodBadgeMapping;
import com.groom.sumbisori.domain.badge.repository.SeafoodMappingQueryRepository;
import com.groom.sumbisori.domain.collection.dto.event.CollectionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class CollectionBadgeService {
    private final SeafoodMappingQueryRepository seafoodMappingQueryRepository;
    private final RankedBadgeGrantService rankedService;
    private final SpecialBadgeGrantService specialService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void awardCollectionBadge(CollectionEvent event) {
        log.info("해산물 배지 조건 확인 시작 - 사용자 ID: {}, 해산물 ID 목록: {}", event.userId(), event.seafoodIds());
        List<BadgeLevel> grantedBadgeLevels = new ArrayList<>();
        Map<BadgeLevel, List<Long>> specialMapping = new HashMap<>();
        Long userId = event.userId();

        List<SeafoodBadgeMapping> mappings = seafoodMappingQueryRepository.findBySeafoodIds(event.seafoodIds());
        for (SeafoodBadgeMapping mapping : mappings) {
            BadgeLevel badgeLevel = mapping.getBadgeLevel();
            Badge badge = badgeLevel.getBadge();

            switch (badge.getType()) {
                case RANKED -> {
                    if (rankedService.process(userId, mapping)) {
                        grantedBadgeLevels.add(badgeLevel);
                    }
                }
                case SPECIAL -> {
                    specialMapping
                            .computeIfAbsent(badgeLevel, k -> new ArrayList<>())
                            .add(mapping.getSeafoodId());
                }
                default -> {
                    log.warn("지원하지 않는 뱃지 타입: (badgeId={})", badge.getId());
                }
            }
        }
        for (Map.Entry<BadgeLevel, List<Long>> entry : specialMapping.entrySet()) {
            BadgeLevel badgeLevel = entry.getKey();
            List<Long> requiredIds = entry.getValue();
            if (specialService.process(userId, badgeLevel, requiredIds)) {
                grantedBadgeLevels.add(badgeLevel);
            }
        }
        // 추후 발급된 배지에 대해 알림 이벤트
        // 발급된 배지에 대한 알림 이벤트를 발송하는 로직을 추가할 수 있습니다.
        // 예: badgeNotificationService.sendBadgeNotification(userId, grantedBadgeLevels);
    }
}
