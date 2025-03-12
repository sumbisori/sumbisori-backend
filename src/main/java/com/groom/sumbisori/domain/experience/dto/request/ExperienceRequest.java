package com.groom.sumbisori.domain.experience.dto.request;

import com.groom.sumbisori.domain.collection.dto.request.CollectionRequest;
import com.groom.sumbisori.domain.experience.domain.CompanionType;
import com.groom.sumbisori.domain.experience.domain.Experience;
import com.groom.sumbisori.domain.experience.domain.Weather;
import com.groom.sumbisori.domain.file.dto.request.FileRequest;
import com.groom.sumbisori.domain.place.entity.Place;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public record ExperienceRequest(@NotNull(message = "체험 날짜는 필수입니다.") LocalDate experienceDate,

                                @NotNull(message = "장소 ID는 필수입니다.") @Positive(message = "장소 ID는 양수여야 합니다.") Long placeId,

                                @NotNull(message = "날씨 정보는 필수입니다.") Weather weather,

                                @NotNull(message = "동반자 유형은 필수입니다.") CompanionType companionType,

                                @Size(max = 10, message = "최대 10개의 파일을 요청할 수 있습니다.") List<FileRequest> files,

                                @NotNull(message = "체험 소감은 필수입니다.") @Size(min = 10, max = 150, message = "최소 10자 ~ 최대 150자까지 입력할 수 있습니다.") String impression,

                                @NotNull(message = "만족도는 필수입니다.") @Min(value = 1, message = "만족도는 1점 이상이어야 합니다.") @Max(value = 5, message = "만족도는 5점을 초과할 수 없습니다.") int satisfaction,

                                @Size(max = 5, message = "최대 5개의 수집을 요청할 수 있습니다.") List<CollectionRequest> collections) {
    public Experience of(Long userId, Place place, ExperienceRequest experienceRequest) {
        return Experience.builder().userId(userId).place(place).experienceDate(experienceRequest.experienceDate())
                .weather(experienceRequest.weather()).companionType(experienceRequest.companionType())
                .impression(experienceRequest.impression()).satisfaction(experienceRequest.satisfaction()).build();
    }
}
