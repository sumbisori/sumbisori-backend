package com.groom.sumbisori.domain.reservation.dto;

import com.groom.sumbisori.domain.reservation.entity.Reservation.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

@Builder
public record ReservationDto(Long id, String reservationDate, String reservationTime, Status status, String personName,
                             int peopleCount, String imageUrl, String name, String address) {
    @QueryProjection
    public ReservationDto {
    }
}
