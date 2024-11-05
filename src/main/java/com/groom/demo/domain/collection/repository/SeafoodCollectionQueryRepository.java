package com.groom.demo.domain.collection.repository;

import static com.groom.demo.domain.collection.entity.QSeafoodCollection.seafoodCollection;
import static com.groom.demo.domain.seafood.entity.QSeafood.seafood;

import com.groom.demo.domain.collection.dto.response.MyCollectionSeafood;
import com.groom.demo.domain.collection.dto.response.QMyCollectionSeafood;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeafoodCollectionQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<MyCollectionSeafood> findSeafoodCollectionByUserId(Long userId) {
        return queryFactory
                .select(new QMyCollectionSeafood(
                        seafood.id,
                        seafood.koreanName,
                        seafood.englishName,
                        seafoodCollection.quantity.sum().intValue()
                ))
                .from(seafoodCollection)
                .join(seafoodCollection.seafood, seafood).on(seafoodCollection.userId.eq(userId))
                .groupBy(seafood.id, seafood.koreanName, seafood.englishName)
                .fetch();
    }
}
