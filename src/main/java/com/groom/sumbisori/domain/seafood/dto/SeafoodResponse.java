package com.groom.sumbisori.domain.seafood.dto;

import com.groom.sumbisori.domain.seafood.entity.Seafood;

public record SeafoodResponse(Long seafoodId, String koreanName, String englishName, String description) {
    public static SeafoodResponse from(Seafood seafood) {
        return new SeafoodResponse(seafood.getId(), seafood.getKoreanName(), seafood.getEnglishName(), seafood.getDescription());
    }
}
