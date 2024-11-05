package com.groom.demo.domain.reservation.service;

import com.groom.demo.domain.place.entity.Place;
import com.groom.demo.domain.place.repository.PlaceRepository;
import com.groom.demo.domain.reservation.dto.ReservationCount;
import com.groom.demo.domain.reservation.dto.ReservationDto;
import com.groom.demo.domain.reservation.dto.ReservationRequest;
import com.groom.demo.domain.reservation.entity.Reservation;
import com.groom.demo.domain.reservation.entity.Reservation.Status;
import com.groom.demo.domain.reservation.repository.ReservationQueryRepository;
import com.groom.demo.domain.reservation.repository.ReservationRepository;
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

    @Transactional
    public void createReservation(Long userId, ReservationRequest reservationRequest) {
        // 예약 생성 로직을 추가합니다.
        Place place = placeRepository.findById(reservationRequest.getPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("장소를 찾을 수 없습니다."));
        Reservation reservation = Reservation.builder()
                .reservationDate(reservationRequest.getSelectedAvailableDate())
                .reservationTime(reservationRequest.getSelectedTime())
                .status(Status.PENDING)
                .name(reservationRequest.getPersonName())
                .place(place)
                .numberOfPeople(reservationRequest.getPeopleCount())
                .userId(userId)
                .build();
        reservationRepository.save(reservation);
    }

    public ReservationCount getMyReservationCount(Long userId) {
        int pendingCount = reservationRepository.countByUserIdAndStatus(userId, Status.PENDING);
        int endCount = reservationRepository.countByUserIdAndStatus(userId, Status.END);
        return new ReservationCount(pendingCount, endCount);
    }
}
