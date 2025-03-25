package com.groom.sumbisori.domain.experience.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Weather {
    CLEAR_SKY("맑음"),
    FEW_CLOUDS("구름 조금"),
    SCATTERED_CLOUDS("흩어진 구름"),
    BROKEN_CLOUDS("구름 많음"),
    SHOWER_RAIN("소나기"),
    RAIN("비"),
    THUNDERSTORM("뇌우"),
    SNOW("눈"),
    MIST("안개");

    private final String label;
}
