package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.common.util.JsonUtil;
import com.groom.sumbisori.domain.place.entity.Place;
import java.util.Map;

public record PlaceResponse(Long placeId, String name, String address,
                            int price, Map<String, String> desc, String imageUrl, double latitude, double longitude,
                            String phoneNumber,
                            String link) {
    public static PlaceResponse from(Place place) {
        return new PlaceResponse(
                place.getId(),
                place.getName(),
                place.getAddress(),
                place.getPrice(),
                JsonUtil.parseJsonToMap(place.getDescription()),
                place.getImageUrl(),
                place.getLatitude(),
                place.getLongitude(),
                place.getPhoneNumber(),
                place.getLink()
        );
    }
}
