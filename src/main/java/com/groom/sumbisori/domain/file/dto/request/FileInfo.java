package com.groom.sumbisori.domain.file.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record FileInfo(
        @NotNull(message = "파일 타입(Content-Type)은 필수입니다.") String contentType,
        @NotNull(message = "파일 크기는 필수입니다.")
        @Min(value = 1, message = "파일 크기는 최소 1바이트여야 합니다.") // 최소 크기 1바이트
        @Max(value = 10 * 1024 * 1024, message = "파일 크기는 최대 10MB를 초과할 수 없습니다.") Long size // 최대 10MB
) {
}
