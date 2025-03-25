package com.groom.sumbisori.domain.experience.domain;

import com.groom.sumbisori.domain.base.entity.BaseTimeEntity;
import com.groom.sumbisori.domain.place.entity.Place;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
        name = "experience",
        indexes = {
                @Index(name = "idx_experience_user_id", columnList = "user_id"),
                @Index(name = "idx_user_date", columnList = "user_id, experience_date DESC")
        }
)
public class Experience extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id")
    private Long id;

    @Column
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(nullable = false)
    private LocalDate experienceDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Weather weather;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanionType companionType;

    @Column(length = 1000)
    private String impression;

    @Column(nullable = false)
    private int satisfaction;

}
