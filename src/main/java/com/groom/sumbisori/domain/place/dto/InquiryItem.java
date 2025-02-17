package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.PlaceDescription;

public record InquiryItem(
        String title,
        String content,
        String iconUrl
) {
    public static InquiryItem from(PlaceDescription placeDescription) {
        return new InquiryItem(
                placeDescription.getTitle(),
                placeDescription.getContent(),
                placeDescription.getIcon().getUrl()
        );
    }
}
