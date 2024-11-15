package com.groom.sumbisori.domain.reservation.repository;

import com.groom.sumbisori.domain.reservation.entity.Reservation;
import com.groom.sumbisori.domain.reservation.entity.Reservation.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    int countByUserIdAndStatus(Long userId, Status status);

    @Modifying
    @Query("DELETE FROM Reservation r WHERE r.userId = :userId")
    void deleteByUserId(Long userId);
}
