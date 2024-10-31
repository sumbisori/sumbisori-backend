package com.groom.demo.domain.reservation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserIdAndStatus(Long userId, Status status);

    int countByUserIdAndStatus(Long userId, Status status);
}
