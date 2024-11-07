package com.groom.sumbisori.domain.reservation.entity;

import com.groom.sumbisori.domain.base.entity.BaseTimeEntity;
import com.groom.sumbisori.domain.place.entity.Place;
import com.groom.sumbisori.domain.reservation.error.ReservationErrorCode;
import com.groom.sumbisori.domain.reservation.error.exception.ReservationException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@DynamicUpdate
@NoArgsConstructor
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int numberOfPeople;

    @Column(nullable = false)
    private String reservationDate;

    @Column(nullable = false)
    private String reservationTime;

    @Column(columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    private Status status; // 예약 완료 여부

    public boolean isOwner(Long userId) {
        return this.userId.equals(userId);
    }

    public void complete() {
        if (isEnded()) {
            throw new ReservationException(ReservationErrorCode.RESERVATION_ALREADY_COMPLETED);
        }
        this.status = Status.END;
    }

    public boolean isEnded() {
        return this.status == Status.END;
    }

    public enum Status {
        PENDING, END
    }
}
