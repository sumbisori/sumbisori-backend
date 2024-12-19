package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.Place;
import java.util.Arrays;
import java.util.List;

public record PlaceResponse(Long placeId, String name, String address,
                            int price, String desc, String imageUrl, double latitude, double longitude, List<String> phoneNumber,
                            String link) {

    private static final String PHONE_NUMBER_DELIMITER = ",";

    public static PlaceResponse from(Place place) {
        return new PlaceResponse(
                place.getId(),
                place.getName(),
                place.getAddress(),
                place.getPrice(),
                place.getDescription(),
                place.getImageUrl(),
                place.getLatitude(),
                place.getLongitude(),
                splitPhoneNumber(place.getPhoneNumber()),
                place.getLink()
        );
    }

    private static List<String> splitPhoneNumber(String phoneNumber) {
        return Arrays.stream(phoneNumber.split(PHONE_NUMBER_DELIMITER)).toList();
    }
}
