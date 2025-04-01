package com.groom.sumbisori.domain.collection.service;

import com.groom.sumbisori.domain.collection.dto.response.SeafoodCollectionInfo;
import com.groom.sumbisori.domain.collection.dto.response.MySeafoodCollectionInfo;
import com.groom.sumbisori.domain.collection.dto.response.MySeafoodCollectionStatus;
import com.groom.sumbisori.domain.collection.repository.CollectionQueryRepository;
import com.groom.sumbisori.domain.seafood.entity.Seafood;
import com.groom.sumbisori.domain.seafood.service.SeafoodCacheManager;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollectionLookupService {
    private final SeafoodCacheManager seafoodCacheManager;
    private final CollectionQueryRepository collectionQueryRepository;

    /**
     * 나의 해산물 컬렉션 조회
     */
    public List<SeafoodCollectionInfo> getMySeafoodCollection(Long userId) {
        if (userId == null) { // 로그인 안한 경우
            return List.of();
        }
        return collectionQueryRepository.findTotalQuantityBySeafoodForUser(userId);
    }

    /**
     * 해산물 수집 현황 조회
     */
    public List<MySeafoodCollectionStatus> getSeafoodCollectionStatus(Long userId) {
        List<Seafood> allSeafoods = seafoodCacheManager.getAllSeafoods();
        Map<Long, MySeafoodCollectionInfo> myCollections = getUserCollections(userId);
        return allSeafoods.stream()
                .map(seafood -> MySeafoodCollectionStatus.of(seafood, myCollections.get(seafood.getId())))
                .toList();
    }

    private Map<Long, MySeafoodCollectionInfo> getUserCollections(Long userId) {
        if (userId == null) {
            return Collections.emptyMap(); // 비로그인 시 빈 Map 반환
        }

        return collectionQueryRepository.findCollectedSeafoodByUserId(userId)
                .stream()
                .collect(Collectors.toMap(MySeafoodCollectionInfo::seafoodId, info -> info));
    }
}
