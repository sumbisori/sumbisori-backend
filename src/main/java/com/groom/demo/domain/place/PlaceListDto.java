package com.groom.demo.domain.place;

import java.util.List;

public record PlaceListDto(String value, String name, String address,
                           int price, String desc, String imageUrl, List<String> availableDate) {
}
