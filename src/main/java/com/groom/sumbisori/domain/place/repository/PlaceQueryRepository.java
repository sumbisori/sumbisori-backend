package com.groom.sumbisori.domain.place.repository;

import static com.groom.sumbisori.domain.place.entity.QPlace.place;
import static com.groom.sumbisori.domain.place.entity.QPlaceDescription.placeDescription;
import static com.groom.sumbisori.domain.place.entity.QIcon.icon;

import com.groom.sumbisori.domain.place.entity.Place;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlaceQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<Place> findById(Long placeId) {
        Place result = queryFactory.selectFrom(place)
                .leftJoin(place.descriptions, placeDescription).fetchJoin()
                .leftJoin(placeDescription.icon, icon).fetchJoin()
                .where(place.id.eq(placeId))
                .fetchOne();
        return Optional.ofNullable(result);
    }

    public List<Place> findAll() {
        return queryFactory.selectFrom(place)
                .leftJoin(place.descriptions, placeDescription).fetchJoin()
                .distinct()
                .fetch();
    }
}
