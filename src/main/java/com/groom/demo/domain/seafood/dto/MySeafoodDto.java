package com.groom.demo.domain.seafood.dto;

import com.groom.demo.domain.seafood.Seafood;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record MySeafoodDto(Seafood seafood, String name, String englishName, String description,
                           String insDt, int count) {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy년 M월 d일");

    public MySeafoodDto(Seafood seafood, String name, String englishName, String description,
                        LocalDateTime insDt, int count) {
        this(seafood, name, englishName, description,
                insDt != null ? insDt.format(FORMATTER) : null, // null이면 변환하지 않음
                count);
    }
}
