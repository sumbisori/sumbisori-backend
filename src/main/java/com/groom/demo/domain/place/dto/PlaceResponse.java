package com.groom.demo.domain.place.dto;

import com.groom.demo.domain.place.entity.Place;
import java.util.List;

public record PlaceResponse(Long placeId, String name, String address,
                            int price, String desc, String imageUrl, List<String> availableDate,
                            double latitude, double longitude) {
    public static PlaceResponse of(Place place, List<String> availableDate) {
        return new PlaceResponse(
                place.getId(),
                place.getName(),
                place.getAddress(),
                place.getPrice(),
                place.getDescription(),
                place.getImageUrl(),
                availableDate,
                place.getLatitude(),
                place.getLongitude()
        );
    }
}
