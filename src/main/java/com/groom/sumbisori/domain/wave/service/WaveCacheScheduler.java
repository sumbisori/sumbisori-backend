package com.groom.sumbisori.domain.wave.service;

import com.groom.sumbisori.domain.content.entity.Spot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Profile("!local")
public class WaveCacheScheduler implements ApplicationListener<ApplicationReadyEvent> {
    private final WaveRefreshService waveRefreshService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Initializing wave info for all spots");
        refreshAllSpots();
    }

    @Scheduled(cron = "0 5,35 * * * ?")
    public void refreshAllSpots() {
        log.info("Refreshing wave info for all spots started");
        for (Spot spot : Spot.values()) {
            waveRefreshService.refresh(spot);
        }
    }
}
