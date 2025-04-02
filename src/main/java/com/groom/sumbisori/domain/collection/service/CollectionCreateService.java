package com.groom.sumbisori.domain.collection.service;

import com.groom.sumbisori.domain.collection.dto.request.CollectionRequest;
import com.groom.sumbisori.domain.collection.entity.SeafoodCollection;
import com.groom.sumbisori.domain.collection.repository.CollectionRepository;
import com.groom.sumbisori.domain.collectionitem.service.CollectionItemCreateService;
import com.groom.sumbisori.domain.file.entity.RefType;
import com.groom.sumbisori.domain.file.service.FileImageCreateService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionCreateService {
    private final FileImageCreateService fileImageCreateService;
    private final CollectionItemCreateService collectionItemCreateService;
    private final CollectionRepository collectionRepository;

    @Transactional
    public void create(Long userId, List<CollectionRequest> seafoodCollectionRequests, LocalDate experienceDate,
                       Long experienceId) {
        for (CollectionRequest request : seafoodCollectionRequests) {
            SeafoodCollection seafoodCollection = SeafoodCollection.builder()
                    .experienceId(experienceId)
                    .userId(userId)
                    .collectedAt(experienceDate)
                    .build();
            collectionRepository.save(seafoodCollection);
            collectionItemCreateService.create(seafoodCollection.getId(), request.collectionInfos());
            fileImageCreateService.uploadSingleImage(request.imageIdentifier(), userId, RefType.SEAFOOD_COLLECTION,
                    seafoodCollection.getId());
        }
    }
}
