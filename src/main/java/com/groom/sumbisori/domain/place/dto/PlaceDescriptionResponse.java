package com.groom.sumbisori.domain.place.dto;

public record PlaceDescriptionResponse(String title, String description) {
    public static PlaceDescriptionResponse from(String title, String description) {
        return new PlaceDescriptionResponse(title, description);
    }
}
