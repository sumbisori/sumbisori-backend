package com.groom.sumbisori.domain.reservation.repository;

import com.groom.sumbisori.domain.reservation.entity.Reservation;
import com.groom.sumbisori.domain.reservation.entity.Reservation.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserIdAndStatus(Long userId, Status status);

    int countByUserIdAndStatus(Long userId, Status status);
}
