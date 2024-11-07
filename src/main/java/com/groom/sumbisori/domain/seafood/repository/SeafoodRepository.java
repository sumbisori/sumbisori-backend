package com.groom.sumbisori.domain.seafood.repository;

import com.groom.sumbisori.domain.seafood.entity.Seafood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeafoodRepository extends JpaRepository<Seafood, Long> {
}
