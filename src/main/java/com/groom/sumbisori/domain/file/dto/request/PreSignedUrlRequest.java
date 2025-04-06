package com.groom.sumbisori.domain.file.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record PreSignedUrlRequest(
        @NotNull(message = "파일 정보는 필수입니다.")
        @Size(min = 1, max = 10, message = "최소 1개, 최대 10개의 파일을 요청할 수 있습니다.")
        List<FileInfo> fileInfos
) {
}
