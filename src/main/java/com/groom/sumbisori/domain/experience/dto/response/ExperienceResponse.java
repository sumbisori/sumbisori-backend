package com.groom.sumbisori.domain.experience.dto.response;

import com.groom.sumbisori.domain.experience.dto.ExperienceQueryDto;
import com.groom.sumbisori.domain.file.service.FileUrlConvertService;
import java.time.LocalDate;

public record ExperienceResponse(
        Long experienceId,
        LocalDate experienceDate,
        String companion,
        String weather,
        String imageUrl
) {
    public static ExperienceResponse from(ExperienceQueryDto dto) {
        return new ExperienceResponse(
                dto.experienceId(),
                dto.experienceDate(),
                dto.companionType().getLabel(),
                dto.weather().getLabel(),
                FileUrlConvertService.convert(dto.imageIdentifier())
        );
    }
}
