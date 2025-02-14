package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.Place;

public record PlaceResponse(Long placeId, String name, String address,
                            int minPrice, int maxPrice, PlaceDescriptionResponse details, String imageUrl,
                            double latitude, double longitude, String phoneNumber,
                            String link, String reservationLink) {
    public static PlaceResponse from(Place place) {
        return new PlaceResponse(
                place.getId(),
                place.getName(),
                place.getAddress(),
                place.getMinPrice(),
                place.getMaxPrice(),
                PlaceDescriptionResponse.from(place.getDescriptions()),
                place.getImageUrl(),
                place.getLatitude(),
                place.getLongitude(),
                place.getPhoneNumber(),
                place.getLink(),
                place.getReservationLink()
        );
    }
}
