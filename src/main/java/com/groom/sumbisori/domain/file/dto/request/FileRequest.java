package com.groom.sumbisori.domain.file.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record FileRequest(
        @NotNull(message = "파일의 imageIdentifier는 필수입니다.")
        String imageIdentifier,

        @NotNull(message = "파일의 순서는 필수입니다.")
        @Min(value = 1, message = "파일의 순서는 1 이상이어야 합니다.")
        @Max(value = 10, message = "파일의 순서 10 까지 가능합니다.")
        int sequence) {
}
