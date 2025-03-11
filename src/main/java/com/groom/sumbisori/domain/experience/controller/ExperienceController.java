package com.groom.sumbisori.domain.experience.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.domain.experience.dto.request.ExperienceRequest;
import com.groom.sumbisori.domain.experience.dto.response.ExperienceResponse;
import com.groom.sumbisori.domain.experience.service.ExperienceCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiences")
@RequiredArgsConstructor
public class ExperienceController implements ExperienceApi {
    private final ExperienceCreateService experienceService;

    @PostMapping
    public ResponseEntity<ExperienceResponse> createExperience(@LoginUser Long userId,
                                                               @RequestBody @Valid ExperienceRequest experienceRequest) {
        return ResponseEntity.ok(experienceService.create(userId, experienceRequest));
    }

}
