package com.groom.sumbisori.domain.content.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Youtube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "youtube_id")
    private Long id;

    @Column
    private String videoId;

    @Column
    private String title;

    @Column
    private String channelTitle;

    @Column
    private String thumbnailUrl;

    @Column
    private BigInteger viewCount;

    @Column
    private LocalDateTime publishTime;
}
