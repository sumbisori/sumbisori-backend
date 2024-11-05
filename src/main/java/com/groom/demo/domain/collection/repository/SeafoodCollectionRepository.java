package com.groom.demo.domain.collection.repository;

import com.groom.demo.domain.collection.entity.SeafoodCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeafoodCollectionRepository extends JpaRepository<SeafoodCollection, Long> {
}
