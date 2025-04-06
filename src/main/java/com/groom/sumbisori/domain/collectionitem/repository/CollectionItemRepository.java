package com.groom.sumbisori.domain.collectionitem.repository;

import com.groom.sumbisori.domain.collectionitem.entity.CollectionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionItemRepository extends JpaRepository<CollectionItem, Long> {
}
