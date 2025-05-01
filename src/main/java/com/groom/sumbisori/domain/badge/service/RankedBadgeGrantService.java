package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.groom.sumbisori.domain.badge.entity.SeafoodBadgeMapping;
import com.groom.sumbisori.domain.badge.entity.UserBadge;
import com.groom.sumbisori.domain.badge.repository.UserBadgeRepository;
import com.groom.sumbisori.domain.collection.repository.CollectionQueryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankedBadgeGrantService {
    private final UserBadgeRepository userBadgeRepository;
    private final CollectionQueryRepository collectionQueryRepository;

    @Transactional
    public boolean process(Long userId, SeafoodBadgeMapping mapping) {
        Long seafoodId = mapping.getSeafoodId();
        BadgeLevel badgeLevel = mapping.getBadgeLevel();

        if (alreadyHas(userId, badgeLevel)) {
            return false;
        }
        if (!isBadgeCriteriaMet(userId, seafoodId, badgeLevel)) {
            return false;
        }
        grantBadgeToUser(userId, badgeLevel);
        return true;
    }

    /**
     * 이미 보유하고 있는 배지인지 확인
     */
    private boolean alreadyHas(Long userId, BadgeLevel badgeLevel) {
        return userBadgeRepository.existsByUserIdAndBadgeLevelId(userId, badgeLevel.getId());
    }

    /**
     * 배지 조건 수량을 만족하는지 확인
     */
    private boolean isBadgeCriteriaMet(Long userId, Long seafoodId, BadgeLevel badgeLevel) {
        int totalSeafoodCount = collectionQueryRepository.countTotalQuantityByUserIdAndSeafoodId(userId, seafoodId);
        return badgeLevel.isSatisfiedBy(totalSeafoodCount);
    }

    /**
     * 배지 부여
     */
    private void grantBadgeToUser(Long userId, BadgeLevel badgeLevel) {
        UserBadge userBadge = UserBadge.builder()
                .userId(userId)
                .badgeLevel(badgeLevel)
                .build();
        userBadgeRepository.save(userBadge);
    }

}
