package com.groom.sumbisori.domain.alarm.context;

import com.groom.sumbisori.domain.badge.entity.BadgeType;

public record BadgeAlarmContext(Long badgeId, BadgeType badgeType, String name, int level) implements AlarmContext {
    public static BadgeAlarmContext of(Long badgeId, BadgeType type, String name, int level) {
        return new BadgeAlarmContext(badgeId, type, name, level);
    }
}
