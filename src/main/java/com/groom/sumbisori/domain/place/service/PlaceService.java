package com.groom.sumbisori.domain.place.service;

import com.groom.sumbisori.domain.place.dto.PlaceResponse;
import com.groom.sumbisori.domain.place.entity.Place;
import com.groom.sumbisori.domain.place.error.PlaceErrorcode;
import com.groom.sumbisori.domain.place.error.exception.PlaceException;
import com.groom.sumbisori.domain.place.repository.PlaceRepository;
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
    private final PlaceRepository placeRepository;

    public List<PlaceResponse> getAllPlaces() {
        return placeRepository.findAll().stream()
                .map(place -> PlaceResponse.from(place))
                .toList();
    }

    public PlaceResponse getPlaceById(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceException(PlaceErrorcode.PLACE_NOT_FOUND));
        return PlaceResponse.from(place);
    }
}
