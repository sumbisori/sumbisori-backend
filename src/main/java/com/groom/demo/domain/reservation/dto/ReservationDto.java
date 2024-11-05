package com.groom.demo.domain.reservation.dto;

import com.groom.demo.domain.reservation.entity.Status;
import lombok.Builder;

@Builder
public record ReservationDto(Long id, String reservationDate, String reservationTime, Status status, String personName,
                             String imageUrl, String name, String address, int peopleCount) {
}
