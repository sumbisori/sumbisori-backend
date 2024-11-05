package com.groom.demo.domain.seafood.service;

import com.groom.demo.domain.seafood.dto.MySeafoodDto;
import com.groom.demo.domain.seafood.dto.SeafoodResponse;
import com.groom.demo.domain.seafood.repository.SeafoodQueryRepository;
import com.groom.demo.domain.seafood.repository.SeafoodRepository;
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
