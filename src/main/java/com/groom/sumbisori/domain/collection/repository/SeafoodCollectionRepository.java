package com.groom.sumbisori.domain.collection.repository;

import com.groom.sumbisori.domain.collection.entity.SeafoodCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SeafoodCollectionRepository extends JpaRepository<SeafoodCollection, Long> {
    @Modifying
    @Query("DELETE FROM SeafoodCollection sc WHERE sc.userId = :userId")
    void deleteByUserId(Long userId);
}
