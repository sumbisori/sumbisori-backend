package com.groom.sumbisori.domain.reservation.repository;

import static com.groom.sumbisori.domain.place.entity.QPlace.place;
import static com.groom.sumbisori.domain.reservation.entity.QReservation.reservation;

import com.groom.sumbisori.domain.reservation.dto.QReservationDto;
import com.groom.sumbisori.domain.reservation.dto.ReservationDto;
import com.groom.sumbisori.domain.reservation.entity.Reservation.Status;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<ReservationDto> findByUserIdAndStatus(Long userId, Status status) {
        return queryFactory.select(
                new QReservationDto(
                        reservation.id,
                        reservation.reservationDate,
                        reservation.reservationTime,
                        reservation.status,
                        reservation.name,
                        reservation.numberOfPeople,
                        place.imageUrl,
                        place.name,
                        place.address
                ))
                .from(reservation)
                .join(reservation.place, place)
                .where(reservation.userId.eq(userId)
                        .and(reservation.status.eq(status)))
                .fetch();
    }
}
