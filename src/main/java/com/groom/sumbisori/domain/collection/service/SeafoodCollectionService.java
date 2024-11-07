package com.groom.sumbisori.domain.collection.service;

import com.groom.sumbisori.domain.collection.dto.response.MyCollectionSeafood;
import com.groom.sumbisori.domain.collection.entity.SeafoodCollection;
import com.groom.sumbisori.domain.collection.repository.SeafoodCollectionQueryRepository;
import com.groom.sumbisori.domain.collection.repository.SeafoodCollectionRepository;
import com.groom.sumbisori.domain.seafood.dto.SeafoodRequest;
import com.groom.sumbisori.domain.seafood.entity.Seafood;
import com.groom.sumbisori.domain.seafood.error.SeafoodErrorCode;
import com.groom.sumbisori.domain.seafood.error.exception.SeafoodException;
import com.groom.sumbisori.domain.seafood.repository.SeafoodRepository;
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
                .orElseThrow(() -> new SeafoodException(SeafoodErrorCode.SEAFOOD_NOT_FOUND));
        SeafoodCollection seafoodCollection = SeafoodCollection.builder()
                .userId(userId)
                .seafood(seafood)
                .quantity(seafoodRequest.getCount())
                .build();
        seafoodCollectionRepository.save(seafoodCollection);
    }
}
