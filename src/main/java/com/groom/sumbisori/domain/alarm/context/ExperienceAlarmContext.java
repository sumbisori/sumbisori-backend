package com.groom.sumbisori.domain.alarm.context;

public record ExperienceAlarmContext(Long experienceId) implements AlarmContext {
    public static ExperienceAlarmContext of(Long experienceId) {
        return new ExperienceAlarmContext(experienceId);
    }
}
