package com.groom.sumbisori.domain.collection.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CollectionRequest(
        @NotNull(message = "파일의 imageIdentifier는 필수입니다.")
        String imageIdentifier,

        @NotNull(message = "seafoodId는 필수입니다.")
        Long seafoodId,

        @NotNull(message = "quantity는 필수입니다.")
        @Min(value = 1, message = "quantity는 1 이상이어야 합니다.")
        @Max(value = 99, message = "quantity는 99 이하 입니다.")
        int quantity) {
}
