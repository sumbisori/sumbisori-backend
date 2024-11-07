package com.groom.sumbisori.domain.place.repository;

import com.groom.sumbisori.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
