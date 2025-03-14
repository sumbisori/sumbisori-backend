package com.groom.sumbisori.domain.collectionitem.service;

import com.groom.sumbisori.domain.collection.dto.request.CollectionInfo;
import com.groom.sumbisori.domain.collectionitem.entity.CollectionItem;
import com.groom.sumbisori.domain.collectionitem.error.CollectionItemErrorcode;
import com.groom.sumbisori.domain.collectionitem.error.CollectionItemException;
import com.groom.sumbisori.domain.collectionitem.repository.CollectionItemRepository;
import com.groom.sumbisori.domain.seafood.entity.Seafood;
import com.groom.sumbisori.domain.seafood.error.SeafoodErrorCode;
import com.groom.sumbisori.domain.seafood.error.exception.SeafoodException;
import com.groom.sumbisori.domain.seafood.repository.SeafoodRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionItemCreateService {
    private final SeafoodRepository seafoodRepository;
    private final CollectionItemRepository collectionItemRepository;

    /**
     * CollectionItem을 생성
     */
    @Transactional
    public void create(Long collectionId, List<CollectionInfo> collectionInfos) {
        // 1. 해산물이 먼저 중복이 없는가?
        validateDuplicateSeafood(collectionInfos);

        // 2. 요청한 seafoodId가 모두 존재하는가?
        Map<Long, Seafood> seafoodMap = fetchSeafoodMap(collectionInfos);

        // 3. CollectionItem 생성
        List<CollectionItem> items = collectionInfos.stream()
                .map(info -> createCollectionItem(collectionId, info, seafoodMap))
                .toList();

        collectionItemRepository.saveAll(items);
    }

    /**
     * 중복된 해산물이 있는지 확인
     */
    private void validateDuplicateSeafood(List<CollectionInfo> collectionInfos) {
        Set<Long> requestedSeafoodIds = collectionInfos.stream()
                .map(CollectionInfo::seafoodId)
                .collect(Collectors.toSet());

        if (requestedSeafoodIds.size() != collectionInfos.size()) {
            throw new CollectionItemException(CollectionItemErrorcode.DUPLICATE_SEAFOOD);
        }
    }

    /**
     * seafoodId에 해당하는 해산물 정보를 가져옴
     */
    private Map<Long, Seafood> fetchSeafoodMap(List<CollectionInfo> collectionInfos) {
        Set<Long> seafoodIds = collectionInfos.stream()
                .map(CollectionInfo::seafoodId)
                .collect(Collectors.toSet());

        List<Seafood> seafoodList = seafoodRepository.findAllById(seafoodIds);

        Map<Long, Seafood> seafoodMap = seafoodList.stream()
                .collect(Collectors.toMap(Seafood::getId, seafood -> seafood));

        if (seafoodMap.size() != seafoodIds.size()) {
            throw new SeafoodException(SeafoodErrorCode.SEAFOOD_NOT_FOUND);
        }
        return seafoodMap;
    }

    private static CollectionItem createCollectionItem(Long collectionId, CollectionInfo info,
                                                       Map<Long, Seafood> seafoodMap) {
        return CollectionItem.builder()
                .seafoodCollectionId(collectionId)
                .seafood(seafoodMap.get(info.seafoodId()))
                .quantity(info.quantity())
                .build();
    }
}
