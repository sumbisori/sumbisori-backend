package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.PlaceDescription;

public record InquiryItem(
        String title,
        String content,
        String iconUrl
) {
    public static InquiryItem from(PlaceDescription placeDescription, String cloudfrontDomain) {
        return new InquiryItem(
                placeDescription.getTitle(),
                placeDescription.getContent(),
                cloudfrontDomain + placeDescription.getIcon().getUrl()
        );
    }
}
