package com.groom.sumbisori.domain.user.entity;

import com.groom.sumbisori.domain.base.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@Builder
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(name = "unique_provider_id_type", columnNames = {"providerId",
                "providerType"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    @Column(columnDefinition = "varchar(20)")
    private String providerType;
    @Column
    private String providerId;

    @Column(columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column
    private String nickname;
    @Column
    private String profileImageUrl;

    @Column
    private LocalDateTime lastLoginAt;

    @Column
    private Long badgeLevelId;

    public void update(String email, String nickname, String profileImageUrl) {
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.lastLoginAt = LocalDateTime.now();
    }

    public void updateRepBadgeLevel(Long badgeLevelId) {
        this.badgeLevelId = badgeLevelId;
    }

    @Getter
    @AllArgsConstructor
    public enum UserRole {
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");

        private final String value;
    }
}
