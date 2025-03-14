package com.groom.sumbisori.domain.collection.dto.response;

import com.groom.sumbisori.domain.seafood.entity.Seafood;
import java.time.format.DateTimeFormatter;

public record MySeafoodCollectionStatus(Long seafoodId, String koreanName, String englishName, String description,
                                        String insDt, int count) {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

    private static final String NOT_COLLECTED_YET = "아직 수집하지 못했어요";

    public static MySeafoodCollectionStatus of(Seafood seafood, MySeafoodCollectionInfo info) {
        return new MySeafoodCollectionStatus(
                seafood.getId(),
                seafood.getKoreanName(),
                seafood.getEnglishName(),
                seafood.getDescription(),
                (info != null) ? info.collectedAt().format(DATE_FORMATTER) : NOT_COLLECTED_YET,
                (info != null) ? info.count() : 0
        );
    }

}
