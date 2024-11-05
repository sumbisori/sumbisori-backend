package com.groom.demo.domain.reservation.service;

import com.groom.demo.domain.reservation.entity.Status;
import com.groom.demo.domain.reservation.dto.ReservationCount;
import com.groom.demo.domain.reservation.dto.ReservationDto;
import com.groom.demo.domain.reservation.dto.ReservationRequest;
import com.groom.demo.domain.reservation.entity.Reservation;
import com.groom.demo.domain.reservation.repository.ReservationRepository;
import com.groom.demo.domain.user.entity.User;
import com.groom.demo.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createReservation(Long userId, ReservationRequest reservationRequest) {
        // 예약 생성 로직을 추가합니다.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Reservation reservation = Reservation.builder()
                .reservationDate(reservationRequest.getSelectedAvailableDate())
                .reservationTime(reservationRequest.getSelectedTime())
                .status(Status.PENDING)
                .name(reservationRequest.getPersonName())
                .place(reservationRequest.getPlace())
                .numberOfPeople(reservationRequest.getPeopleCount())
                .userId(user.getId())
                .build();
        reservationRepository.save(reservation);
    }

    public List<ReservationDto> getMyReservations(Long userId, Status status) {
        List<Reservation> list = reservationRepository.findByUserIdAndStatus(userId, status);
        return list.stream()
                .map(reservation -> ReservationDto.builder()
                        .id(reservation.getId())
                        .reservationDate(reservation.getReservationDate())
                        .reservationTime(reservation.getReservationTime())
                        .status(reservation.getStatus())
                        .personName(reservation.getName())
                        .imageUrl(reservation.getPlace().getImageUrl())
                        .name(reservation.getPlace().getName())
                        .address(reservation.getPlace().getAddress())
                        .peopleCount(reservation.getNumberOfPeople())
                        .build())
                .toList();
    }

    public ReservationCount getMyReservationCount(Long userId) {
        int pendingCount = reservationRepository.countByUserIdAndStatus(userId, Status.PENDING);
        int endCount = reservationRepository.countByUserIdAndStatus(userId, Status.END);
        return new ReservationCount(pendingCount, endCount);
    }
}
