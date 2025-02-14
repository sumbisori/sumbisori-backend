package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.PlaceDescription;
import com.groom.sumbisori.domain.place.entity.PlaceDescription.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record PlaceDescriptionResponse(
        List<DescriptionItem> operationInfo,
        List<DescriptionItem> facilities,
        List<DescriptionItem> inquiries
) {
    public static PlaceDescriptionResponse from(List<PlaceDescription> descriptions) {
        // 한 번의 스트림 처리로 타입별 분류
        Map<Type, List<DescriptionItem>> groupedDescriptions = descriptions.stream()
                .collect(Collectors.groupingBy(
                        PlaceDescription::getType,
                        Collectors.mapping(DescriptionItem::from, Collectors.toList())
                ));

        return new PlaceDescriptionResponse(
                groupedDescriptions.getOrDefault(Type.INFO, List.of()),
                groupedDescriptions.getOrDefault(Type.FACILITY, List.of()),
                groupedDescriptions.getOrDefault(Type.INQUIRY, List.of())
        );
    }
}
