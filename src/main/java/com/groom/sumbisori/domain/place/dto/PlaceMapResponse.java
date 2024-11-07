package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.Place;

public record PlaceMapResponse(Long placeId, String name, String address,
                               int price, String desc, String imageUrl, double latitude, double longitude) {
    public static PlaceMapResponse from(Place place) {
        return new PlaceMapResponse(
                place.getId(),
                place.getName(),
                place.getAddress(),
                place.getPrice(),
                place.getDescription(),
                place.getImageUrl(),
                place.getLatitude(),
                place.getLongitude()
        );
    }
}
