package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.groom.sumbisori.domain.collection.repository.CollectionQueryRepository;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialBadgeGrantService {
    private final BadgeCreateService badgeCreateService;
    private final BadgeGrantValidator badgeGrantValidator;
    private final CollectionQueryRepository collectionQueryRepository;

    @Transactional
    public void process(Long userId, BadgeLevel badgeLevel, List<Long> requiredIds) {
        if (badgeGrantValidator.isGrantable(userId, badgeLevel,
                () -> isRequiredSeafoodAllCollected(userId, requiredIds))) {
            badgeCreateService.create(userId, badgeLevel);
        }
    }

    private boolean isRequiredSeafoodAllCollected(Long userId, List<Long> requiredIds) {
        Set<Long> collected = new HashSet<>(collectionQueryRepository.findDistinctCollectedSeafoodIds(userId));
        return collected.containsAll(requiredIds);
    }
}
