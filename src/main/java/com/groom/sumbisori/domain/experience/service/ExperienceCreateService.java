package com.groom.sumbisori.domain.experience.service;

import com.groom.sumbisori.domain.collection.service.CollectionCreateService;
import com.groom.sumbisori.domain.experience.domain.Experience;
import com.groom.sumbisori.domain.experience.dto.request.ExperienceRequest;
import com.groom.sumbisori.domain.experience.dto.response.ExperienceResponse;
import com.groom.sumbisori.domain.experience.error.ExperienceErrorcode;
import com.groom.sumbisori.domain.experience.error.ExperienceException;
import com.groom.sumbisori.domain.experience.repository.ExperienceRepository;
import com.groom.sumbisori.domain.file.service.FileImageCreateService;
import com.groom.sumbisori.domain.place.entity.Place;
import com.groom.sumbisori.domain.place.error.PlaceErrorcode;
import com.groom.sumbisori.domain.place.error.exception.PlaceException;
import com.groom.sumbisori.domain.place.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExperienceCreateService {
    private static final int YEARS_LIMIT = 5;

    private final PlaceRepository placeRepository;
    private final ExperienceRepository experienceRepository;
    private final FileImageCreateService fileImageCreateService;
    private final CollectionCreateService collectionCreateService;

    @Transactional
    public ExperienceResponse create(Long userId, ExperienceRequest experienceRequest) {
        // 1. 검사
        validate(experienceRequest);

        // 2. 체험 생성
        Place place = placeRepository.findById(experienceRequest.placeId())
                .orElseThrow(() -> new PlaceException(PlaceErrorcode.PLACE_NOT_FOUND));
        Experience experience = experienceRequest.of(userId, place, experienceRequest);
        experienceRepository.save(experience);
        Long experienceId = experience.getId();

        // 3. 체험 이미지 생성
        fileImageCreateService.create(experienceRequest.files(), experienceId);

        // 4. 수집 생성
        collectionCreateService.create(userId, experienceRequest.collections(),
                experienceRequest.experienceDate(), experienceId);
        return new ExperienceResponse(experienceId);
    }

    private void validate(ExperienceRequest experienceRequest) {
        LocalDate experienceDate = experienceRequest.experienceDate();
        LocalDate today = LocalDate.now();
        LocalDate fiveYearsAgo = today.minusYears(YEARS_LIMIT);
        if (experienceDate.isAfter(today) || experienceDate.isBefore(fiveYearsAgo)) {
            throw new ExperienceException(ExperienceErrorcode.EXPERIENCE_DATE_INVALID);
        }
    }
}
