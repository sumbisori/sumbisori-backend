package com.groom.sumbisori.domain.collection.repository;

import com.groom.sumbisori.domain.collection.entity.SeafoodCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<SeafoodCollection, Long> {
}
