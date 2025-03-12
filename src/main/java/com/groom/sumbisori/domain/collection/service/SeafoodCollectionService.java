package com.groom.sumbisori.domain.collection.service;

import com.groom.sumbisori.domain.collection.dto.response.MyCollectionSeafood;
import com.groom.sumbisori.domain.collection.repository.SeafoodCollectionQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeafoodCollectionService {
    private final SeafoodCollectionQueryRepository seafoodCollectionQueryRepository;

    public List<MyCollectionSeafood> mySeafoodCollection(Long userId) {
        return seafoodCollectionQueryRepository.findSeafoodCollectionByUserId(userId);
    }
}
