package com.groom.sumbisori.common.util;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.groom.sumbisori.common.constant.CacheType;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.caffeine.CaffeineCache;

public class CacheUtil {
    public static CaffeineCache from(CacheType cacheType) {
        return new CaffeineCache(cacheType.getCacheName(),
                Caffeine.newBuilder().recordStats()
                        .expireAfterWrite(cacheType.getExpireSeconds(), TimeUnit.SECONDS)
                        .maximumSize(cacheType.getMaxSize())
                        .build());
    }
}
