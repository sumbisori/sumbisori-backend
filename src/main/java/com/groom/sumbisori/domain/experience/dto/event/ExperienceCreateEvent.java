package com.groom.sumbisori.domain.experience.dto.event;

public record ExperienceCreateEvent(Long userId, Long experienceId) {
    public static ExperienceCreateEvent of(Long userId, Long experienceId) {
        return new ExperienceCreateEvent(userId, experienceId);
    }
}
