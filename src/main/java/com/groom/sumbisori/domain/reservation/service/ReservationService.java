package com.groom.sumbisori.domain.reservation.service;

import com.groom.sumbisori.domain.place.entity.Place;
import com.groom.sumbisori.domain.place.error.PlaceErrorcode;
import com.groom.sumbisori.domain.place.error.exception.PlaceException;
import com.groom.sumbisori.domain.place.repository.PlaceRepository;
import com.groom.sumbisori.domain.reservation.dto.ReservationCount;
import com.groom.sumbisori.domain.reservation.dto.ReservationDto;
import com.groom.sumbisori.domain.reservation.dto.ReservationRequest;
import com.groom.sumbisori.domain.reservation.entity.Reservation;
import com.groom.sumbisori.domain.reservation.entity.Reservation.Status;
import com.groom.sumbisori.domain.reservation.error.ReservationErrorCode;
import com.groom.sumbisori.domain.reservation.error.exception.ReservationException;
import com.groom.sumbisori.domain.reservation.repository.ReservationQueryRepository;
import com.groom.sumbisori.domain.reservation.repository.ReservationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationQueryRepository reservationQueryRepository;
    private final PlaceRepository placeRepository;

    public List<ReservationDto> getMyReservations(Long userId, Status status) {
        return reservationQueryRepository.findByUserIdAndStatus(userId, status);
    }

    public ReservationCount getMyReservationCount(Long userId) {
        int pendingCount = reservationRepository.countByUserIdAndStatus(userId, Status.PENDING);
        int endCount = reservationRepository.countByUserIdAndStatus(userId, Status.END);
        return new ReservationCount(pendingCount, endCount);
    }

    @Transactional
    public void createReservation(Long userId, ReservationRequest reservationRequest) {
        Place place = placeRepository.findById(reservationRequest.placeId())
                .orElseThrow(() -> new PlaceException(PlaceErrorcode.PLACE_NOT_FOUND));
        Reservation reservation = reservationRequest.of(userId, place);
        reservationRepository.save(reservation);
    }

    @Transactional
    public void completeReservation(Long userId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationException(ReservationErrorCode.RESERVATION_NOT_FOUND));
        if (!reservation.isOwner(userId)) {
            throw new ReservationException(ReservationErrorCode.RESERVATION_FORBIDDEN);
        }
        reservation.complete();
    }

    @Transactional
    public void cancelReservation(Long userId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationException(ReservationErrorCode.RESERVATION_NOT_FOUND));
        if (!reservation.isOwner(userId)) {
            throw new ReservationException(ReservationErrorCode.RESERVATION_FORBIDDEN);
        }
        if (reservation.isEnded()) {
            throw new ReservationException(ReservationErrorCode.RESERVATION_ALREADY_COMPLETED);
        }
        reservationRepository.delete(reservation);
    }
}
