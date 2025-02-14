package com.groom.sumbisori.domain.place.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groom.sumbisori.domain.place.entity.PlaceDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DescriptionItem(
        String title,
        String content,
        String description,
        String iconUrl
) {
    public static DescriptionItem from(PlaceDescription placeDescription) {
        return new DescriptionItem(
                placeDescription.getTitle(),
                placeDescription.getContent(),
                placeDescription.getDescription(),
                placeDescription.getIcon().getUrl()
        );
    }
}
