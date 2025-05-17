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
        Map<BadgeLevel, List<Long>> specialMapping = new HashMap<>(); // 특별 배지 매핑 <배지레벨, 필요한 해산물 ID 목록>
        Long userId = event.userId();

        List<SeafoodBadgeMapping> mappings = seafoodMappingQueryRepository.findBySeafoodIds(event.seafoodIds());
        for (SeafoodBadgeMapping mapping : mappings) {
            BadgeLevel badgeLevel = mapping.getBadgeLevel();
            Badge badge = badgeLevel.getBadge();

            switch (badge.getType()) {
                case RANKED -> rankedService.process(userId, mapping);
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
        grantSpecialBadges(specialMapping, userId);
    }

    private void grantSpecialBadges(Map<BadgeLevel, List<Long>> specialMapping, Long userId) {
        specialMapping.entrySet().stream()
                .forEach(e -> specialService.process(userId, e.getKey(), e.getValue()));
    }
}
