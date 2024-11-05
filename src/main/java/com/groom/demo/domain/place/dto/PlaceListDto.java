package com.groom.demo.domain.place.dto;

import java.util.List;

public record PlaceListDto(String value, String name, String address,
                           int price, String desc, String imageUrl, List<String> availableDate,
                           int x, int y) {
}
