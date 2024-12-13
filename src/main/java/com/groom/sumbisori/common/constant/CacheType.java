package com.groom.sumbisori.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {
    PLACES("places", 60 * 60 * 24 * 30, 20),
    PLACE_DETAILS("placeDetails", 60 * 60 * 24 * 30, 20),
    YOUTUBES("youtubes", 60 * 60 * 24, 60);

    private final String cacheName;
    private final int expireSeconds;
    private final int maxSize;
}
