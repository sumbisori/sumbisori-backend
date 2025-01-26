package com.groom.sumbisori.domain.weather.dto;

import com.groom.sumbisori.domain.content.entity.Suitability;
import com.groom.sumbisori.domain.weather.entity.WeatherType;

public record WeatherResponse(double temperature, WeatherType weatherType, Suitability suitability) {
    public static WeatherResponse of(double temp, WeatherType weatherType) {
        return new WeatherResponse(temp, weatherType, weatherType.getSuitability());
    }
}
