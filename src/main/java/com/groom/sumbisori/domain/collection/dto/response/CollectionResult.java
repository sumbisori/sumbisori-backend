package com.groom.sumbisori.domain.collection.dto.response;

import com.groom.sumbisori.domain.file.service.FileUrlConvertService;
import java.util.List;

public record CollectionResult(String imageUrl,
                               List<SeafoodCollectionInfo> seafoodCollectionInfos) {
    public CollectionResult {
        imageUrl = FileUrlConvertService.convert(imageUrl);
    }
}
