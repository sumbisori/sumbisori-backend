package com.groom.sumbisori.domain.collection.service;

import com.groom.sumbisori.domain.collection.dto.request.CollectionRequest;
import com.groom.sumbisori.domain.collection.entity.SeafoodCollection;
import com.groom.sumbisori.domain.collection.repository.SeafoodCollectionRepository;
import com.groom.sumbisori.domain.collectionitem.service.CollectionItemCreateService;
import com.groom.sumbisori.domain.file.service.FileCopyService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeafoodCollectionCreateService {
    private final FileCopyService fileCopyService;
    private final CollectionItemCreateService collectionItemCreateService;
    private final SeafoodCollectionRepository seafoodCollectionRepository;

    @Transactional
    public void create(Long userId, List<CollectionRequest> seafoodCollectionRequests, Long experienceId) {
        for (CollectionRequest request : seafoodCollectionRequests) {
            fileCopyService.copy(request.imageIdentifier());
            SeafoodCollection seafoodCollection = SeafoodCollection.builder()
                    .experienceId(experienceId)
                    .imageIdentifier(request.imageIdentifier())
                    .build();
            seafoodCollectionRepository.save(seafoodCollection);
            collectionItemCreateService.create(userId, seafoodCollection.getId(), request.collectionInfos());
        }
    }
}
