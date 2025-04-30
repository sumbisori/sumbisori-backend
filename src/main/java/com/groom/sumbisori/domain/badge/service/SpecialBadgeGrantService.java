package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.groom.sumbisori.domain.badge.entity.UserBadge;
import com.groom.sumbisori.domain.badge.repository.UserBadgeRepository;
import com.groom.sumbisori.domain.collection.repository.CollectionQueryRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialBadgeGrantService {
    private final UserBadgeRepository userBadgeRepository;
    private final CollectionQueryRepository collectionQueryRepository;

    @Transactional
    public boolean process(Long userId, BadgeLevel badgeLevel, List<Long> requiredIds) {
        if (alreadyHas(userId, badgeLevel)) {
            return false;
        }

        List<Long> collectedSeafoodIds = collectionQueryRepository.findDistinctCollectedSeafoodIds(userId);
        if (!collectedSeafoodIds.containsAll(requiredIds)) {
            return false;
        }

        userBadgeRepository.save(UserBadge.builder()
                .userId(userId)
                .badgeLevel(badgeLevel)
                .build());

        return true;
    }

    private boolean alreadyHas(Long userId, BadgeLevel badgeLevel) {
        return userBadgeRepository.existsByUserIdAndBadgeLevelId(userId, badgeLevel.getId());
    }
}
