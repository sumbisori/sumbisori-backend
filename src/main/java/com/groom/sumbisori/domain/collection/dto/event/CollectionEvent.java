package com.groom.sumbisori.domain.collection.dto.event;

import java.util.List;

public record CollectionEvent(
        Long userId,
        List<Long> seafoodIds
) {
}
