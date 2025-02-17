package com.groom.sumbisori.domain.place.dto;

import com.groom.sumbisori.domain.place.entity.PlaceDescription;
import com.groom.sumbisori.domain.place.entity.PlaceDescription.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record PlaceDescriptionResponse(
        List<OperationInfoItem> operationInfo,
        List<FacilityItem> facilities,
        List<InquiryItem> inquiries
) {
    public static PlaceDescriptionResponse from(List<PlaceDescription> descriptions) {
        Map<Type, List<PlaceDescription>> grouped = descriptions.stream()
                .collect(Collectors.groupingBy(PlaceDescription::getType));

        return new PlaceDescriptionResponse(
                grouped.getOrDefault(Type.INFO, List.of()).stream()
                        .map(OperationInfoItem::from).toList(),
                grouped.getOrDefault(Type.FACILITY, List.of()).stream()
                        .map(FacilityItem::from).toList(),
                grouped.getOrDefault(Type.INQUIRY, List.of()).stream()
                        .map(InquiryItem::from).toList());
    }
}
