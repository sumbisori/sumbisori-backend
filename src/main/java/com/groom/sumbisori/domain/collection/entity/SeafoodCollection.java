package com.groom.sumbisori.domain.collection.entity;

import com.groom.sumbisori.domain.base.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
@Table(name = "seafood_collection",
        indexes = {
                @Index(name = "idx_seafood_collection_experience", columnList = "experience_id"),
                @Index(name = "idx_seafood_collection_user", columnList = "user_id"),
        })
public class SeafoodCollection extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seafood_collection_id")
    private Long id;

    @Column(nullable = false)
    private Long experienceId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String imageIdentifier;

    @Column(nullable = false)
    private LocalDate collectedAt;
}
