package com.groom.demo.domain.seafood.repository;

import com.groom.demo.domain.seafood.entity.Seafood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeafoodRepository extends JpaRepository<Seafood, Long> {
}
