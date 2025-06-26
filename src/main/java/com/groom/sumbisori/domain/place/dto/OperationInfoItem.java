package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.PlaceDescription;

public record OperationInfoItem(
        String title,
        String content,
        String description,
        String iconUrl
) {
    public static OperationInfoItem from(PlaceDescription placeDescription, String cloudfrontDomain) {
        return new OperationInfoItem(
                placeDescription.getTitle(),
                placeDescription.getContent(),
                placeDescription.getDescription(),
                cloudfrontDomain + placeDescription.getIcon().getUrl()
        );
    }
}
