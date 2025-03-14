package com.groom.sumbisori.domain.collection.service;

import com.groom.sumbisori.domain.collection.dto.request.CollectionRequest;
import com.groom.sumbisori.domain.collection.entity.SeafoodCollection;
import com.groom.sumbisori.domain.collection.repository.CollectionRepository;
import com.groom.sumbisori.domain.collectionitem.service.CollectionItemCreateService;
import com.groom.sumbisori.domain.file.service.FileCopyService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionCreateService {
    private final FileCopyService fileCopyService;
    private final CollectionItemCreateService collectionItemCreateService;
    private final CollectionRepository collectionRepository;

    @Transactional
    public void create(Long userId, List<CollectionRequest> seafoodCollectionRequests, LocalDate experienceDate,
                       Long experienceId) {
        for (CollectionRequest request : seafoodCollectionRequests) {
            fileCopyService.copy(request.imageIdentifier());
            SeafoodCollection seafoodCollection = SeafoodCollection.builder()
                    .experienceId(experienceId)
                    .imageIdentifier(request.imageIdentifier())
                    .userId(userId)
                    .collectedAt(experienceDate)
                    .build();
            collectionRepository.save(seafoodCollection);
            collectionItemCreateService.create(seafoodCollection.getId(), request.collectionInfos());
        }
    }
}
