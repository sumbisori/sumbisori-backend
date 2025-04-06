package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.Place;

public record SimplePlaceResponse(Long placeId, String name, String city, String imageUrl) {
    private static final int CITY_INDEX = 1;

    public static SimplePlaceResponse from(Place place) {
        return new SimplePlaceResponse(
                place.getId(),
                place.getName(),
                getCityFromAddress(place.getAddress()),
                place.getImageUrl()
        );
    }

    private static String getCityFromAddress(String address) {
        String[] addressParts = address.split(" ");
        return addressParts.length > CITY_INDEX ? addressParts[CITY_INDEX] : "";
    }
}
