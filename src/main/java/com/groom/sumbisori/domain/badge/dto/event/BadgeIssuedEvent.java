package com.groom.sumbisori.domain.badge.dto.event;

import com.groom.sumbisori.domain.badge.entity.Badge;
import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.groom.sumbisori.domain.badge.entity.BadgeType;

public record BadgeIssuedEvent(Long userId, Long badgeId, BadgeType type, String name, int level
) {
    public static BadgeIssuedEvent of(Long userId, Badge badge, BadgeLevel badgeLevel) {
        return new BadgeIssuedEvent(
                userId,
                badge.getId(),
                badge.getType(),
                badge.getName(),
                badgeLevel.getLevel()
        );
    }
}
