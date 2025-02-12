package com.groom.sumbisori.domain.place.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private int minPrice;

    @Column
    private int maxPrice;

    @Column(length = 1000)
    private String imageUrl;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private String phoneNumber;

    @Column
    private String link;

    @Column
    private String reservationLink;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<PlaceDescription> descriptions = new ArrayList<>();
}
