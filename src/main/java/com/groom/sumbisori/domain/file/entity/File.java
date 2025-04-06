package com.groom.sumbisori.domain.file.entity;

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

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "file",
        indexes = {
                @Index(name = "idx_file_ref",
                        columnList = "ref_id, ref_type, sequence, image_identifier")
        }
)
public class File extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ref_type", nullable = false, length = 50)
    private RefType refType;

    @Column(name = "ref_id", nullable = false)
    private Long refId;

    @Column(nullable = false, unique = true)
    private String imageIdentifier;

    @Column(nullable = false)
    private int sequence;

    public boolean isOwner(Long userId) {
        return this.userId.equals(userId);
    }
}
