package com.groom.sumbisori.domain.wave.dto;

import com.groom.sumbisori.domain.content.entity.Suitability;
import java.time.LocalDateTime;

public record WaveResponse(
        double waveHeight,
        double waterTemperature,
        LocalDateTime observationTime,
        Suitability waveHeightSuitability,
        Suitability waterTemperatureSuitability
) {
    private static final double WAVE_HEIGHT_SUITABLE = 2.0;
    private static final double WAVE_HEIGHT_CAUTION = 4.0;

    private static final double WATER_TEMP_SUITABLE_MIN = 17.0;
    private static final double WATER_TEMP_SUITABLE_MAX = 25.0;
    private static final double WATER_TEMP_CAUTION_MIN = 10.0;
    private static final double WATER_TEMP_CAUTION_MAX = 28.0;

    public static WaveResponse of(double waveHeight, double waterTemperature, LocalDateTime observationTime) {
        return new WaveResponse(
                waveHeight,
                waterTemperature,
                observationTime,
                calculateWaveHeightSuitability(waveHeight),
                calculateWaterTemperatureSuitability(waterTemperature)
        );
    }

    private static Suitability calculateWaveHeightSuitability(double waveHeight) {
        if (waveHeight < WAVE_HEIGHT_SUITABLE) {
            return Suitability.SUITABLE;
        } else if (waveHeight < WAVE_HEIGHT_CAUTION) {
            return Suitability.CAUTION;
        }
        return Suitability.DANGEROUS;
    }

    private static Suitability calculateWaterTemperatureSuitability(double waterTemperature) {
        if (waterTemperature >= WATER_TEMP_SUITABLE_MIN && waterTemperature <= WATER_TEMP_SUITABLE_MAX) {
            return Suitability.SUITABLE;
        } else if ((waterTemperature > WATER_TEMP_CAUTION_MIN && waterTemperature < WATER_TEMP_SUITABLE_MIN) ||
                (waterTemperature > WATER_TEMP_SUITABLE_MAX && waterTemperature < WATER_TEMP_CAUTION_MAX)) {
            return Suitability.CAUTION;
        }
        return Suitability.DANGEROUS;
    }
}
