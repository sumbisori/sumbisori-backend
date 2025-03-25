package com.groom.sumbisori.domain.experience.dto;

import com.groom.sumbisori.domain.experience.domain.CompanionType;
import com.groom.sumbisori.domain.experience.domain.Weather;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ExperienceDetailQueryDto {
    private Long experienceId;
    private LocalDate experienceDate;
    private int satisfaction;
    private CompanionType companionType;
    private Weather weather;
    private String impression;
    private LocalDateTime createdAt;
    private String placeName;
    private List<String> imageIdentifiers = new ArrayList<>();

    public ExperienceDetailQueryDto(Long experienceId, LocalDate experienceDate, int satisfaction,
                                    CompanionType companionType, Weather weather, String impression,
                                    LocalDateTime createdAt, String placeName) {
        this.experienceId = experienceId;
        this.experienceDate = experienceDate;
        this.satisfaction = satisfaction;
        this.companionType = companionType;
        this.weather = weather;
        this.impression = impression;
        this.createdAt = createdAt;
        this.placeName = placeName;
    }

    public void addImageIdentifiers(List<String> imageIdentifiers) {
        this.imageIdentifiers.addAll(imageIdentifiers);
    }
}
