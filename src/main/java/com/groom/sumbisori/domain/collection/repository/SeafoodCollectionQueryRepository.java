package com.groom.sumbisori.domain.collection.repository;

import static com.groom.sumbisori.common.util.QuerydslUtil.nullSafeBuilder;
import static com.groom.sumbisori.domain.collection.entity.QSeafoodCollection.seafoodCollection;
import static com.groom.sumbisori.domain.seafood.entity.QSeafood.seafood;

import com.groom.sumbisori.domain.collection.dto.response.MyCollectionSeafood;
import com.groom.sumbisori.domain.collection.dto.response.QMyCollectionSeafood;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeafoodCollectionQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<MyCollectionSeafood> findSeafoodCollectionByUserId(Long userId) {
//        return queryFactory
//                .select(new QMyCollectionSeafood(
//                        seafood.id,
//                        seafood.koreanName,
//                        seafood.englishName,
//                        seafoodCollection.quantity.sum().intValue()
//                ))
//                .from(seafoodCollection)
//                .join(seafoodCollection.seafood, seafood).on(seafoodCollectionUserIdEq(userId))
//                .groupBy(seafood.id, seafood.koreanName, seafood.englishName)
//                .fetch();
        return List.of();
    }

    public int sumQuantityByUserId(Long userId) {
//        Integer result = queryFactory
//                .select(seafoodCollection.quantity.sum())
//                .from(seafoodCollection)
//                .where(seafoodCollection.userId.eq(userId))
//                .fetchOne();
//        return result != null ? result : 0;
        return 0; //임시
    }

//    private BooleanBuilder seafoodCollectionUserIdEq(Long userId) {
//        return nullSafeBuilder(() -> seafoodCollection.userId.eq(userId));
//    }
}
