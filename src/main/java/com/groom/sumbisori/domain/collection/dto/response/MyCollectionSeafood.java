package com.groom.sumbisori.domain.collection.dto.response;

import com.querydsl.core.annotations.QueryProjection;

public record MyCollectionSeafood(Long seafoodId, String koreanName, String englishName, int count) {
    @QueryProjection
    public MyCollectionSeafood {
    }
}
