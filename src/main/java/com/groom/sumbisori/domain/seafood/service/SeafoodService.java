package com.groom.sumbisori.domain.seafood.service;

import com.groom.sumbisori.domain.seafood.dto.MySeafoodDto;
import com.groom.sumbisori.domain.seafood.dto.SeafoodResponse;
import com.groom.sumbisori.domain.seafood.repository.SeafoodQueryRepository;
import com.groom.sumbisori.domain.seafood.repository.SeafoodRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeafoodService {
    private final SeafoodQueryRepository seafoodQueryRepository;
    private final SeafoodRepository seafoodRepository;

    public List<SeafoodResponse> getAllSeafoods() {
        return seafoodRepository.findAll().stream()
                .map(SeafoodResponse::from)
                .toList();
    }

    public List<MySeafoodDto> mySeafoodList(Long userId) {
        return seafoodQueryRepository.findAllSeafoodCollectionStatusByUserId(userId);
    }
}
