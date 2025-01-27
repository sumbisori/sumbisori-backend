package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.Place;
import java.util.List;

public record PlaceResponse(Long placeId, String name, String address,
                            int price, List<PlaceDescriptionResponse> description, String imageUrl, double latitude,
                            double longitude,
                            String phoneNumber,
                            String link) {
    public static PlaceResponse from(Place place) {
        return new PlaceResponse(
                place.getId(),
                place.getName(),
                place.getAddress(),
                place.getPrice(),
                place.getDescriptions().stream()
                        .map(desc -> PlaceDescriptionResponse.from(desc.getTitle(),
                                desc.getDescription()))
                        .toList(),
                place.getImageUrl(),
                place.getLatitude(),
                place.getLongitude(),
                place.getPhoneNumber(),
                place.getLink()
        );
    }
}
