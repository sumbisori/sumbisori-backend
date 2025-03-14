package com.groom.sumbisori.domain.seafood.service;

import com.groom.sumbisori.domain.seafood.dto.SeafoodResponse;
import com.groom.sumbisori.domain.seafood.repository.SeafoodRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeafoodService {
    private final SeafoodRepository seafoodRepository;

    @Cacheable(value = "seafoodResponses")
    public List<SeafoodResponse> getAllSeafoods() {
        return seafoodRepository.findAll().stream()
                .map(SeafoodResponse::from)
                .toList();
    }
}
