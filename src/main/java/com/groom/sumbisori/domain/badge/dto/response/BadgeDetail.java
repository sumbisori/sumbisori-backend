package com.groom.sumbisori.domain.badge.dto.response;

import com.groom.sumbisori.domain.badge.entity.BadgeType;
import java.util.List;

public record BadgeDetail(
        Long badgeId,
        BadgeType badgeType,
        String name,
        String description,
        String acquisition,
        List<BadgeLevelDetail> badgeLevelDetails
) {
    
}
