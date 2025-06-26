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
    public static PlaceDescriptionResponse from(List<PlaceDescription> descriptions, String cloudfrontDomain) {
        Map<Type, List<PlaceDescription>> grouped = descriptions.stream()
                .collect(Collectors.groupingBy(PlaceDescription::getType));

        return new PlaceDescriptionResponse(
                grouped.getOrDefault(Type.INFO, List.of()).stream()
                        .map(desc -> OperationInfoItem.from(desc, cloudfrontDomain)).toList(),
                grouped.getOrDefault(Type.FACILITY, List.of()).stream()
                        .map(desc -> FacilityItem.from(desc, cloudfrontDomain)).toList(),
                grouped.getOrDefault(Type.INQUIRY, List.of()).stream()
                        .map(desc -> InquiryItem.from(desc, cloudfrontDomain)).toList());
    }
}
