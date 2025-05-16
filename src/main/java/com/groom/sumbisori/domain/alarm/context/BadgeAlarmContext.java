package com.groom.sumbisori.domain.alarm.context;

import com.groom.sumbisori.domain.badge.entity.Badge;

public record BadgeAlarmContext(Badge badge, int level) implements AlarmContext {
}
