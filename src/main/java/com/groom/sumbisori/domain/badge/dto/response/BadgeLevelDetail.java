package com.groom.sumbisori.domain.badge.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BadgeLevelDetail(Long badgeLevelId, LocalDate acquisitionDate, int level, Boolean isAcquired,
                               String levelDescription, Boolean isRepresentative) {
    public BadgeLevelDetail(Long badgeLevelId, LocalDateTime acquisitionDateTime, int level, Boolean isAcquired,
                            String levelDescription, Boolean isRepresentative) {
        this(
                badgeLevelId,
                acquisitionDateTime != null ? acquisitionDateTime.toLocalDate() : null,
                level,
                isAcquired,
                levelDescription,
                isRepresentative
        );
    }
}
