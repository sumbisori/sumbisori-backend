package com.groom.sumbisori.domain.experience.dto;

import com.groom.sumbisori.domain.experience.domain.CompanionType;
import com.groom.sumbisori.domain.experience.domain.Weather;
import java.time.LocalDate;

public record ExperienceQueryDto(
        Long experienceId,
        LocalDate experienceDate,
        CompanionType companionType,
        Weather weather,
        String imageIdentifier
) {}
