package com.groom.sumbisori.domain.badge.entity;

import java.util.Arrays;

public enum BadgeRankLevel {
    BRONZE(1, "동"),
    SILVER(2, "은"),
    GOLD(3, "금");

    private final int level;
    private final String label;

    BadgeRankLevel(int level, String label) {
        this.level = level;
        this.label = label;
    }

    public static String getLabel(int level) {
        return Arrays.stream(values())
                .filter(v -> v.level == level)
                .findFirst()
                .map(BadgeRankLevel::getLabel)
                .orElseThrow(() -> new IllegalArgumentException("Unknown badge level: " + level));
    }

    public String getLabel() {
        return label;
    }
}
