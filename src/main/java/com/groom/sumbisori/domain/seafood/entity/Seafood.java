package com.groom.sumbisori.domain.seafood.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Seafood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seafood_id")
    private Long id;

    private String koreanName;

    private String englishName;

    private String description;
}
