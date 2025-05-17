package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.dto.event.BadgeIssuedEvent;
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
import org.springframework.context.ApplicationEventPublisher;
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
    private final ApplicationEventPublisher eventPublisher;

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
        grantSpecialBadges(specialMapping, userId, grantedBadgeLevels);
        grantedBadgeLevels.forEach(level -> {
            log.info("✅ 배지 발급 완료 - 사용자 ID: {}, 배지 레벨 ID: {}", userId, level.getId());
            eventPublisher.publishEvent(BadgeIssuedEvent.of(userId, level.getBadge(), level));
        });
    }

    private void grantSpecialBadges(Map<BadgeLevel, List<Long>> specialMapping, Long userId,
                                    List<BadgeLevel> grantedBadgeLevels) {
        specialMapping.entrySet().stream()
                .filter(e -> specialService.process(userId, e.getKey(), e.getValue()))
                .map(Map.Entry::getKey)
                .forEach(grantedBadgeLevels::add);
    }
}
