package com.groom.demo.domain.reservation;

import com.groom.demo.domain.reservation.dto.ReservationDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<ReservationDto> findByUserId(Long userId);
}
