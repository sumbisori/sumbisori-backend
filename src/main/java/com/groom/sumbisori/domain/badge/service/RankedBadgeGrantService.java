package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.groom.sumbisori.domain.badge.entity.SeafoodBadgeMapping;
import com.groom.sumbisori.domain.collection.repository.CollectionQueryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankedBadgeGrantService {
    private final BadgeCreateService badgeCreateService;
    private final BadgeGrantValidator badgeGrantValidator;
    private final CollectionQueryRepository collectionQueryRepository;

    @Transactional
    public void process(Long userId, SeafoodBadgeMapping mapping) {
        Long seafoodId = mapping.getSeafoodId();
        BadgeLevel badgeLevel = mapping.getBadgeLevel();

        if (badgeGrantValidator.isGrantable(userId, badgeLevel,
                () -> isBadgeCriteriaMet(userId, seafoodId, badgeLevel))) {
            badgeCreateService.create(userId, badgeLevel);
        }
    }

    /**
     * 배지 조건 수량을 만족하는지 확인
     */
    private boolean isBadgeCriteriaMet(Long userId, Long seafoodId, BadgeLevel badgeLevel) {
        int totalSeafoodCount = collectionQueryRepository.countTotalQuantityByUserIdAndSeafoodId(userId, seafoodId);
        return badgeLevel.isSatisfiedBy(totalSeafoodCount);
    }

}
