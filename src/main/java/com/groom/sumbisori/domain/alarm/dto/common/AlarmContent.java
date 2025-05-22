package com.groom.sumbisori.domain.alarm.dto.common;

public record AlarmContent(String message, String link) {
    public static AlarmContent of(String message, String link) {
        return new AlarmContent(message, link);
    }
}

