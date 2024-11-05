package com.groom.demo.domain.reservation.entity;

import com.groom.demo.domain.base.entity.BaseTimeEntity;
import com.groom.demo.domain.place.Place;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Place place; //예약 장소

    @Column(nullable = false)
    private String name; // 예약자 이름

    @Column(nullable = false)
    private int numberOfPeople; // 예약 인원 수

    @Column(nullable = false)
    private String reservationDate; // 예약 날짜

    @Column(nullable = false)
    private String reservationTime; // 예약 시간

    @Column(columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    private Status status; // 예약 완료 여부

}
