package com.groom.sumbisori.domain.experience.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.common.dto.PageResponse;
import com.groom.sumbisori.domain.experience.dto.request.ExperienceRequest;
import com.groom.sumbisori.domain.experience.dto.response.ExperienceResponse;
import com.groom.sumbisori.domain.experience.service.ExperienceCreateService;
import com.groom.sumbisori.domain.experience.service.ExperienceLookupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiences")
@RequiredArgsConstructor
public class ExperienceController implements ExperienceApi {
    private final ExperienceLookupService experienceLookupService;
    private final ExperienceCreateService experienceCreateService;

    @Override
    @GetMapping
    public ResponseEntity<PageResponse<ExperienceResponse>> getExperienceByUserId(@LoginUser Long userId,
                                                                                  Pageable pageable) {
        return ResponseEntity.ok(experienceLookupService.lookupByUserId(userId, pageable));
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> createExperience(@LoginUser Long userId,
                                                 @RequestBody @Valid ExperienceRequest experienceRequest) {
        experienceCreateService.create(userId, experienceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
