package com.groom.sumbisori.domain.place.service;

import com.groom.sumbisori.common.config.CloudfrontConfig;
import com.groom.sumbisori.domain.place.dto.PlaceLocationResponse;
import com.groom.sumbisori.domain.place.dto.PlaceResponse;
import com.groom.sumbisori.domain.place.dto.SimplePlaceResponse;
import com.groom.sumbisori.domain.place.entity.Place;
import com.groom.sumbisori.domain.place.error.PlaceErrorcode;
import com.groom.sumbisori.domain.place.error.exception.PlaceException;
import com.groom.sumbisori.domain.place.repository.PlaceQueryRepository;
import com.groom.sumbisori.domain.place.repository.PlaceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceQueryRepository placeQueryRepository;
    private final CloudfrontConfig cloudfrontConfig;

    @Cacheable(cacheNames = "places")
    public List<SimplePlaceResponse> getAllPlaces() {
        return placeQueryRepository.findAll().stream()
                .map(place -> SimplePlaceResponse.from(place, cloudfrontConfig.getDomain()))
                .toList();
    }

    @Cacheable(cacheNames = "placeDetails", key = "#placeId")
    public PlaceResponse getPlaceById(Long placeId) {
        Place place = placeQueryRepository.findById(placeId)
                .orElseThrow(() -> new PlaceException(PlaceErrorcode.PLACE_NOT_FOUND));
        return PlaceResponse.from(place, cloudfrontConfig.getDomain());
    }

    @Cacheable(cacheNames = "placeLocations")
    public List<PlaceLocationResponse> getAllPlaceLocations() {
        return placeRepository.findAll().stream()
                .map(place -> PlaceLocationResponse.from(place))
                .toList();
    }
}
