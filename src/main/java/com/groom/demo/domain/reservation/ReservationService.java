package com.groom.demo.domain.reservation;

import com.groom.demo.domain.place.Place;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    public List<Place> findPlace() {
        return Arrays.stream(Place.values())
                .collect(Collectors.toList());
    }
}
