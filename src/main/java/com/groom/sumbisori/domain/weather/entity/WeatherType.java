package com.groom.sumbisori.domain.weather.entity;

import static com.groom.sumbisori.domain.content.entity.Suitability.*;

import com.groom.sumbisori.domain.content.entity.Suitability;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@AllArgsConstructor
@Slf4j
public enum WeatherType {
    CLEAR_SKY("01", SUITABLE),
    FEW_CLOUDS("02", SUITABLE),
    SCATTERED_CLOUDS("03", SUITABLE),
    BROKEN_CLOUDS("04", CAUTION),
    SHOWER_RAIN("09", DANGEROUS),
    RAIN("10", DANGEROUS),
    THUNDERSTORM("11", DANGEROUS),
    SNOW("13", DANGEROUS),
    MIST("50", CAUTION);

    private static final Map<String, WeatherType> ICON_CODE_MAP =
            Arrays.stream(values())
                    .collect(Collectors.toMap(WeatherType::getIconCode, type -> type));

    private final String iconCode;
    private final Suitability suitability;

    public static WeatherType fromIcon(String icon) {
        if(icon == null || icon.length() < 2) {
            log.error("Invalid icon code: {}", icon);
            return null;
        }
        String iconNumber = icon.substring(0, 2); // "01d" -> "01"
        WeatherType weatherType = ICON_CODE_MAP.get(iconNumber);
        return weatherType;
    }

}
