package com.groom.sumbisori.domain.badge.dto.response;

import com.groom.sumbisori.domain.badge.entity.Badge;
import com.groom.sumbisori.domain.badge.entity.BadgeType;

public record BadgeStatus(
        BadgeType badgeType,
        String badgeName,
        Long badgeId,
        int badgeLevel,
        Boolean isAcquired,
        Boolean isRepresentative
) {
    public static BadgeStatus of(Badge badge, int badgeLevel, boolean isAcquired, boolean isRepresentative) {
        return new BadgeStatus(badge.getType(), badge.getName(), badge.getId(), badgeLevel, isAcquired,
                isRepresentative);
    }
}
