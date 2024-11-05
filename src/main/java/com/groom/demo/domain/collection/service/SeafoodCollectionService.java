package com.groom.demo.domain.collection.service;

import com.groom.demo.domain.collection.repository.SeafoodCollectionQueryRepository;
import com.groom.demo.domain.collection.repository.SeafoodCollectionRepository;
import com.groom.demo.domain.collection.dto.response.MyCollectionSeafood;
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

    public List<MyCollectionSeafood> mySeafoodCollection(Long userId) {
        return seafoodCollectionQueryRepository.findSeafoodCollectionByUserId(userId);
    }

//    @Transactional
//    public void createSeafoodCollection(Long userId, Long seafoodId) {
//        SeafoodCollection seafoodCollection = SeafoodCollection.builder()
//                .userId(userId)
//                .seafoodId(seafoodId)
//                .build();
//        seafoodCollectionRepository.save(seafoodCollection);
//    }
}
