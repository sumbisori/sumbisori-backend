package com.groom.sumbisori.domain.collection.service;

import com.groom.sumbisori.domain.collection.dto.request.CollectionRequest;
import com.groom.sumbisori.domain.collection.entity.SeafoodCollection;
import com.groom.sumbisori.domain.collection.repository.SeafoodCollectionRepository;
import com.groom.sumbisori.domain.file.service.FileCopyService;
import com.groom.sumbisori.domain.seafood.error.SeafoodErrorCode;
import com.groom.sumbisori.domain.seafood.error.exception.SeafoodException;
import com.groom.sumbisori.domain.seafood.repository.SeafoodRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeafoodCollectionCreateService {
    private final FileCopyService fileCopyService;
    private final SeafoodRepository seafoodRepository;
    private final SeafoodCollectionRepository seafoodCollectionRepository;

    public void create(Long userId, List<CollectionRequest> seafoodCollectionRequests, Long experienceId) {
        List<SeafoodCollection> seafoodCollections = seafoodCollectionRequests.stream()
                .map(c -> {
                    fileCopyService.copy(c.imageIdentifier());
                    return SeafoodCollection.builder()
                            .userId(userId)
                            .experienceId(experienceId)
                            .imageIdentifier(c.imageIdentifier())
                            .seafood(seafoodRepository.findById(c.seafoodId())
                                    .orElseThrow(() -> new SeafoodException(SeafoodErrorCode.SEAFOOD_NOT_FOUND)))
                            .quantity(c.quantity())
                            .build();
                })
                .toList();

        seafoodCollectionRepository.saveAll(seafoodCollections);
    }
}
