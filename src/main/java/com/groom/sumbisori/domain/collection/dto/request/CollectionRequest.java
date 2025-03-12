package com.groom.sumbisori.domain.collection.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record CollectionRequest(
        @NotNull(message = "파일의 imageIdentifier는 필수입니다.")
        String imageIdentifier,

        @Size(min = 1, max = 18, message = "최소 1개에서 최대 18개의 해산물을 요청할 수 있습니다.")
        List<CollectionInfo> collectionInfos) {
}
