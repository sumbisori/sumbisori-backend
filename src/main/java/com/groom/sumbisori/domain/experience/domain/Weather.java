package com.groom.sumbisori.domain.experience.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Weather {
    CLEAR_SKY("맑음"),
    FEW_CLOUDS("구름 조금"),
    SCATTERED_CLOUDS("구름 많음"),
    BROKEN_CLOUDS("흐림"),
    SHOWER_RAIN("소나기"),
    RAIN("비"),
    THUNDERSTORM("천둥번개"),
    SNOW("눈"),
    MIST("안개");

    private final String label;
}
