package com.groom.sumbisori.domain.badge.dto.event;

public record SignUpEvent(Long userId) {
    static public SignUpEvent of(Long userId) {
        return new SignUpEvent(userId);
    }
}
