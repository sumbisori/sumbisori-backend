package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.groom.sumbisori.domain.badge.repository.UserBadgeRepository;
import java.util.List;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BadgeGrantValidator {
    private final UserBadgeRepository userBadgeRepository;

    public boolean isGrantable(Long userId, BadgeLevel badgeLevel) {
        return !userBadgeRepository.existsByUserIdAndBadgeLevelId(userId, badgeLevel.getId());
    }

    public boolean isGrantable(Long userId, BadgeLevel badgeLevel, List<Supplier<Boolean>> conditions) {
        if (userBadgeRepository.existsByUserIdAndBadgeLevelId(userId, badgeLevel.getId())) {
            return false;
        }
        return conditions.stream().allMatch(Supplier::get);
    }

    @SafeVarargs
    public final boolean isGrantable(Long userId, BadgeLevel badgeLevel, Supplier<Boolean>... conditions) {
        return isGrantable(userId, badgeLevel, List.of(conditions));
    }
}
