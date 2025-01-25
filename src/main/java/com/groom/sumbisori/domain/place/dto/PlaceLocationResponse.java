package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.Place;

public record PlaceLocationResponse(Long placeId, double latitude, double longitude) {
    public static PlaceLocationResponse from(Place place) {
        return new PlaceLocationResponse(
                place.getId(),
                place.getLatitude(),
                place.getLongitude()
        );
    }
}
