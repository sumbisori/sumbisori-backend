package com.groom.sumbisori.domain.wave.service;

import com.groom.sumbisori.domain.content.entity.Spot;
import com.groom.sumbisori.domain.wave.dto.WaveResponse;
import com.groom.sumbisori.domain.wave.error.WaveErrorcode;
import com.groom.sumbisori.domain.wave.error.WaveException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 외부 API로부터 전달받은 Raw(문자열) 데이터를 파싱하여 WaveResponse 객체로 변환하는 역할을 담당.
 */
@Component
@Slf4j
public class WaveResponseParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    private static final int MINIMUM_LINE_LENGTH = 5;
    private static final int DATA_LINE_INDEX = 3;
    private static final int MINIMUM_FIELD_COUNT = 14;
    private static final int FIELD_WAVE_HEIGHT = 6;
    private static final int FIELD_WATER_TEMPERATURE = 10;
    private static final int FIELD_OBSERVATION_TIME = 1;

    /**
     * 응답 문자열을 분석(파싱)하여 WaveResponse 객체로 변환
     */
    public WaveResponse parse(String response, Spot spot) {
        String[] lines = response.split("\n");
        validateResponseLength(lines, spot);

        String dataLine = lines[DATA_LINE_INDEX].trim();
        String[] fields = dataLine.split(",");
        validateFieldCount(fields, spot);

        double waveHeight = parseToDouble(fields[FIELD_WAVE_HEIGHT]);
        double waterTemperature = parseToDouble(fields[FIELD_WATER_TEMPERATURE]);
        validateData(waveHeight, waterTemperature, spot);
        LocalDateTime observationTime = parseToLocalDateTime(fields[FIELD_OBSERVATION_TIME]);
        return WaveResponse.of(waveHeight, waterTemperature, observationTime);
    }

    /**
     * 응답 라인 수를 검증
     */
    private void validateResponseLength(String[] lines, Spot spot) {
        if (lines.length < MINIMUM_LINE_LENGTH) {
            log.error("파고 데이터 제공 불가 - Spot: {}", spot.getSpotName());
            throw new WaveException(WaveErrorcode.WAVE_DATA_NOT_FOUND);
        }
    }

    /**
     * 데이터 필드 개수 검증
     */
    private void validateFieldCount(String[] fields, Spot spot) {
        if (fields.length < MINIMUM_FIELD_COUNT) {
            log.error("파고 데이터 제공 불가 - Spot: {}", spot.getSpotName());
            throw new WaveException(WaveErrorcode.WAVE_DATA_NOT_FOUND);
        }
    }

    /**
     * 문자열을 double로 변환
     */
    private double parseToDouble(String value) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException ex) {
            log.error("파고 데이터 파싱 실패 - 잘못된 숫자 형식 (값: {})", value);
            throw new WaveException(WaveErrorcode.WAVE_DATA_NOT_FOUND);
        }
    }

    /**
     * 문자열을 LocalDateTime으로 변환
     */
    private LocalDateTime parseToLocalDateTime(String value) {
        try {
            return LocalDateTime.parse(value.trim(), DATE_TIME_FORMATTER);
        } catch (Exception ex) {
            log.error("파고 데이터 파싱 실패 - 잘못된 날짜 형식 (값: {})", value);
            throw new WaveException(WaveErrorcode.WAVE_DATA_NOT_FOUND);
        }
    }

    /**
     * 파싱된 데이터가 비정상적인 경우 예외를 던지기 위한 검증 함수
     */
    private void validateData(double waveHeight, double waterTemperature, Spot spot) {
        if (waveHeight == -99 || waterTemperature == -99) {
            log.error("비정상 파고 데이터 - Spot: {} (파고: {}, 수온: {})", spot.getSpotName(), waveHeight, waterTemperature);
            throw new WaveException(WaveErrorcode.WAVE_DATA_NOT_FOUND);
        }
    }
}
