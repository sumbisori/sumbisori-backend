package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.Place;

public record SimplePlaceResponse(Long placeId, String name, String imageUrl) {
    public static SimplePlaceResponse from(Place place) {
        return new SimplePlaceResponse(
                place.getId(),
                place.getName(),
                place.getImageUrl()
        );
    }
}
