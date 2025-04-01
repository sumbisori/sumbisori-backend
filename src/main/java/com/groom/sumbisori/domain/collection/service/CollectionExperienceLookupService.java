package com.groom.sumbisori.domain.collection.service;

import com.groom.sumbisori.domain.collection.dto.response.CollectionResult;
import com.groom.sumbisori.domain.collection.dto.response.ExperienceCollectionResponse;
import com.groom.sumbisori.domain.collection.dto.response.SeafoodCollection;
import com.groom.sumbisori.domain.collection.repository.CollectionQueryRepository;
import com.groom.sumbisori.domain.experience.domain.Experience;
import com.groom.sumbisori.domain.experience.error.ExperienceErrorcode;
import com.groom.sumbisori.domain.experience.error.ExperienceException;
import com.groom.sumbisori.domain.experience.repository.ExperienceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CollectionExperienceLookupService {
    private final ExperienceRepository experienceRepository;
    private final CollectionQueryRepository collectionQueryRepository;

    /**
     * 특정 체험에 대한 해산물 수집 현황 조회
     */
    @Transactional(readOnly = true)
    public ExperienceCollectionResponse lookupByExperienceId(Long userId, Long experienceId) {
        Experience experience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new ExperienceException(ExperienceErrorcode.EXPERIENCE_NOT_FOUND));
        if (!experience.isMine(userId)) {
            throw new ExperienceException(ExperienceErrorcode.EXPERIENCE_PERMISSION_DENIED);
        }

        // 체험 채집 통계 조회
        List<SeafoodCollection> seafoodCollections = collectionQueryRepository.findStatisticsByExperienceId(
                experienceId);

        // 체험에 대한 해산물 채집 현황 조회
        List<CollectionResult> collectionResult = collectionQueryRepository.findCollectedByExperienceId(experienceId);

        return ExperienceCollectionResponse.of(seafoodCollections, collectionResult);
    }
}
