package com.groom.sumbisori.domain.collection.dto.response;

import java.util.List;

public record ExperienceCollectionResponse(
        List<SeafoodCollectionInfo> seafoodCollectionInfos,
        List<CollectionResult> collectionResult
) {
    public static ExperienceCollectionResponse of(
            List<SeafoodCollectionInfo> seafoodCollectionInfos,
            List<CollectionResult> collectionResult
    ) {
        return new ExperienceCollectionResponse(seafoodCollectionInfos, collectionResult);
    }
}
