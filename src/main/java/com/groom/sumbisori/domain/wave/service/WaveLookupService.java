package com.groom.sumbisori.domain.wave.service;

import com.groom.sumbisori.domain.content.entity.Spot;
import com.groom.sumbisori.domain.wave.dto.WaveResponse;
import com.groom.sumbisori.domain.wave.error.WaveErrorcode;
import com.groom.sumbisori.domain.wave.error.WaveException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaveLookupService {
    @Cacheable(value = "waves", key = "#spot", unless = "#result == null") // 캐시에 존재하지 않으면, 외부 API 호출 하지 않고 에러 처리
    public WaveResponse lookup(Spot spot) {
        throw new WaveException(WaveErrorcode.WAVE_DATA_NOT_FOUND);
    }
}
