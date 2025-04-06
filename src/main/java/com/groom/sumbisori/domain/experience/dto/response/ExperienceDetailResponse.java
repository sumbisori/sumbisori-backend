package com.groom.sumbisori.domain.experience.dto.response;

import com.groom.sumbisori.domain.experience.dto.ExperienceDetailQueryDto;
import com.groom.sumbisori.domain.file.service.FileUrlConvertService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExperienceDetailResponse {
    private Long experienceId;
    private LocalDate experienceDate;
    private int satisfaction;
    private String companion;
    private String weather;
    private String impression;
    private String placeName;
    private LocalDateTime createdAt;
    private List<String> imageUrls;

    public static ExperienceDetailResponse from(ExperienceDetailQueryDto result) {
        return new ExperienceDetailResponse(
                result.getExperienceId(),
                result.getExperienceDate(),
                result.getSatisfaction(),
                result.getCompanionType().getLabel(),
                result.getWeather().getLabel(),
                result.getImpression(),
                result.getPlaceName(),
                result.getCreatedAt(),
                convertImageUrls(result.getImageIdentifiers())
        );
    }

    private static List<String> convertImageUrls(List<String> imageIdentifiers) {
        return imageIdentifiers.stream()
                .map(FileUrlConvertService::convert)
                .toList();
    }
}
