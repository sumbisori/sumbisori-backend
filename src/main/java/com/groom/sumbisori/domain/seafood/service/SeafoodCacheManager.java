package com.groom.sumbisori.domain.seafood.service;

import com.groom.sumbisori.domain.seafood.entity.Seafood;
import com.groom.sumbisori.domain.seafood.repository.SeafoodRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeafoodCacheManager {
    private final SeafoodRepository seafoodRepository;

    @Cacheable(value = "seafoods")
    public List<Seafood> getAllSeafoods() {
        return seafoodRepository.findAll();
    }

}
