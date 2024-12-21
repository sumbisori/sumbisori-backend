package com.groom.sumbisori.domain.badge.dto.response;

import com.groom.sumbisori.domain.badge.entity.BadgeType;

public record MyBadgeStatus(
        BadgeType badgeType,
        String badgeName,
        String badgeDescription,
        Boolean isAcquired
) {
    public static MyBadgeStatus of(BadgeType badgeType, boolean isAcquired) {
        return new MyBadgeStatus(badgeType, badgeType.getName(), badgeType.getDescription(), isAcquired);
    }
}
