package com.groom.sumbisori.domain.alarm.entity;

import com.groom.sumbisori.domain.alarm.dto.common.AlarmContent;
import com.groom.sumbisori.domain.base.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "alarm", indexes = {
        @Index(name = "idx_alarm_user_created", columnList = "user_id, is_deleted, created_at")
})
@DynamicUpdate
public class Alarm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlarmType type;

    @Column(nullable = false)
    private String content;

    @Column
    private String link;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isRead = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public static Alarm create(Long userId, AlarmType type, AlarmContent content) {
        return Alarm.builder()
                .userId(userId)
                .type(type)
                .content(content.message())
                .link(content.link())
                .build();
    }

    public boolean isOwner(Long userId) {
        return this.userId.equals(userId);
    }

    public void read() {
        this.isRead = true;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
