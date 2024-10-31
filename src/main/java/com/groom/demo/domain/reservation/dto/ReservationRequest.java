package com.groom.demo.domain.reservation.dto;

import com.groom.demo.domain.place.Place;
import lombok.Data;

@Data
public class ReservationRequest {
    private Place place;
    private String personName;
    private String selectedAvailableDate;
    private String selectedTime;
    private int peopleCount;
    private String phone;
}
