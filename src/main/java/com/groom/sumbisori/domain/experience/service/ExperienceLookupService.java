package com.groom.sumbisori.domain.experience.service;

import com.groom.sumbisori.common.dto.PageResponse;
import com.groom.sumbisori.domain.experience.dto.ExperienceQueryDto;
import com.groom.sumbisori.domain.experience.dto.response.ExperienceResponse;
import com.groom.sumbisori.domain.experience.repository.ExperienceQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExperienceLookupService {
    private final ExperienceQueryRepository experienceQueryRepository;

    @Transactional(readOnly = true)
    public PageResponse<ExperienceResponse> lookupByUserId(final Long userId, final Pageable pageable) {
        Page<ExperienceQueryDto> result = experienceQueryRepository.findByUserId(userId, pageable);
        return PageResponse.of(result.map(ExperienceResponse::from));
    }
}
