package com.groom.sumbisori.domain.alarm.entity;

import com.groom.sumbisori.domain.alarm.dto.common.AlarmContent;
import com.groom.sumbisori.domain.base.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "alarm", indexes = {
        @Index(name = "idx_alarm_user_created", columnList = "user_id, is_deleted, created_at")
})
public class Alarm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private AlarmType alarmType;

    @Column
    private String content;

    @Column
    private String link;

    @Column
    @Builder.Default
    private boolean isRead = false;

    @Column
    @Builder.Default
    private boolean isDeleted = false;

    public static Alarm create(Long userId, AlarmType type, AlarmContent content) {
        return Alarm.builder()
                .userId(userId)
                .alarmType(type)
                .content(content.message())
                .link(content.link())
                .build();
    }
}
