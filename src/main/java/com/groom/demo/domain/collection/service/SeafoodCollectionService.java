package com.groom.demo.domain.collection.service;

import com.groom.demo.domain.collection.dto.response.MyCollectionSeafood;
import com.groom.demo.domain.collection.entity.SeafoodCollection;
import com.groom.demo.domain.collection.repository.SeafoodCollectionQueryRepository;
import com.groom.demo.domain.collection.repository.SeafoodCollectionRepository;
import com.groom.demo.domain.seafood.dto.SeafoodRequest;
import com.groom.demo.domain.seafood.entity.Seafood;
import com.groom.demo.domain.seafood.repository.SeafoodRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeafoodCollectionService {
    private final SeafoodCollectionRepository seafoodCollectionRepository;
    private final SeafoodCollectionQueryRepository seafoodCollectionQueryRepository;
    private final SeafoodRepository seafoodRepository;


    public List<MyCollectionSeafood> mySeafoodCollection(Long userId) {
        return seafoodCollectionQueryRepository.findSeafoodCollectionByUserId(userId);
    }

    @Transactional
    public void createSeafoodCollection(Long userId, SeafoodRequest seafoodRequest) {
        Seafood seafood = seafoodRepository.findById(seafoodRequest.getSeafoodId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 해산물입니다."));
        SeafoodCollection seafoodCollection = SeafoodCollection.builder()
                .userId(userId)
                .seafood(seafood)
                .quantity(seafoodRequest.getCount())
                .build();
        seafoodCollectionRepository.save(seafoodCollection);
    }
}
