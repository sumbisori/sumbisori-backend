package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.PlaceDescription;

public record FacilityItem(
        String title,
        String iconUrl
) {
    public static FacilityItem from(PlaceDescription placeDescription, String cloudfrontDomain) {
        return new FacilityItem(
                placeDescription.getTitle(),
                cloudfrontDomain + placeDescription.getIcon().getUrl()
        );
    }
}
