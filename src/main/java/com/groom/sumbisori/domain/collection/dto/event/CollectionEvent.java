package com.groom.sumbisori.domain.collection.dto.event;

import java.util.Set;

public record CollectionEvent(
        Long userId,
        Set<Long> seafoodIds
) {
}
