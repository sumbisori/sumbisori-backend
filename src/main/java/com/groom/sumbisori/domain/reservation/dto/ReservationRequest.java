package com.groom.sumbisori.domain.reservation.dto;

import com.groom.sumbisori.domain.place.entity.Place;
import com.groom.sumbisori.domain.reservation.entity.Reservation;
import com.groom.sumbisori.domain.reservation.entity.Reservation.Status;

public record ReservationRequest(Long placeId, String personName, String selectedAvailableDate, String selectedTime,
                                 int peopleCount, String phone) {
    public Reservation of(Long userId, Place place) {
        return Reservation.builder()
                .reservationDate(selectedAvailableDate)
                .reservationTime(selectedTime)
                .status(Status.PENDING)
                .name(personName)
                .place(place)
                .numberOfPeople(peopleCount)
                .userId(userId)
                .build();
    }
}
