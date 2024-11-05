package com.groom.demo.domain.place.service;

import com.groom.demo.domain.place.dto.PlaceResponse;
import com.groom.demo.domain.place.entity.Place;
import com.groom.demo.domain.place.repository.PlaceRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {
    public static final String DAY_FORMAT = "yyyy년 MM월 dd일";
    private final PlaceRepository placeRepository;

    public List<PlaceResponse> getAllPlaces() {
        List<Place> places = placeRepository.findAll();
        List<String> availableDates = getAvailableDays();

        return places.stream()
                .map(place -> PlaceResponse.of(place, availableDates))
                .toList();
    }

    private static List<String> getAvailableDays() {
        LocalDate startDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DAY_FORMAT);

        List<String> nextWeekDates = new ArrayList<>();
        for (int i = 1; i <= 7; i++) { // 7일 간의 날짜 추가
            nextWeekDates.add(startDate.plusDays(i).format(formatter));
        }
        return nextWeekDates;
    }
}
