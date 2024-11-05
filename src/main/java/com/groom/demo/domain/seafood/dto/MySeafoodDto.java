package com.groom.demo.domain.seafood.dto;

import com.querydsl.core.annotations.QueryProjection;

public record MySeafoodDto(Long seafoodId, String koreanName, String englishName, String description,
                           String insDt, int count) {

    @QueryProjection
    public MySeafoodDto {
    }
}
