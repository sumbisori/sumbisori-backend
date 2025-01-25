package com.groom.sumbisori.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {
    PLACES("places", 60 * 60 * 24 * 365, 1), // 1년
    PLACE_DETAILS("placeDetails", 60 * 60 * 24 * 365, 1), // 1년
    PLACE_LOCATIONS("placeLocations", 60 * 60 * 24 * 365, 1), // 1년
    YOUTUBES("youtubes", 60 * 60 * 24, 60),           // 24시간
    WAVES("waves", 60 * 180, 20),                      // 3시간
    WEATHERS("weathers", 60 * 90, 20);                // 1시간 30분

    private final String cacheName;
    private final int expireSeconds;
    private final int maxSize;
}
