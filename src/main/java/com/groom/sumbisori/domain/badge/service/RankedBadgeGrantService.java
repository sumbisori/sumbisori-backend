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
        int total = collectionQueryRepository.countTotalQuantityByUserIdAndSeafoodId(userId, seafoodId);
        if (!badgeLevel.isSatisfiedBy(total)) {
            return false;
        }
        UserBadge userBadge = UserBadge.builder()
                .userId(userId)
                .badgeLevel(badgeLevel)
                .build();
        userBadgeRepository.save(userBadge);
        return true;
    }

    private boolean alreadyHas(Long userId, BadgeLevel badgeLevel) {
        return userBadgeRepository.existsByUserIdAndBadgeLevelId(userId, badgeLevel.getId());
    }
}
