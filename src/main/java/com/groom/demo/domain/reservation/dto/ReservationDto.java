package com.groom.demo.domain.reservation.dto;

import com.groom.demo.domain.place.Place;
import com.groom.demo.domain.reservation.Status;
import lombok.Builder;

@Builder
public record ReservationDto(String reservationDate, String reservationTime, Status status, String personName,
                             Place place, int peopleCount) {
}
