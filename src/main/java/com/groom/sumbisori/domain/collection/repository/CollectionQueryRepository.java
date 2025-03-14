package com.groom.sumbisori.domain.collection.repository;

import com.groom.sumbisori.domain.collection.dto.response.MySeafoodCollection;
import com.groom.sumbisori.domain.collection.dto.response.MySeafoodCollectionInfo;
import static com.groom.sumbisori.domain.collection.entity.QSeafoodCollection.seafoodCollection;
import static com.groom.sumbisori.domain.collectionitem.entity.QCollectionItem.collectionItem;
import static com.groom.sumbisori.domain.seafood.entity.QSeafood.seafood;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CollectionQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * 사용자가 수집한 해산물 목록 조회
     */
    public List<MySeafoodCollection> findTotalQuantityBySeafoodForUser(Long userId) {
        return queryFactory
                .select(Projections.constructor(MySeafoodCollection.class,
                        seafood.id,
                        seafood.koreanName,
                        seafood.englishName,
                        collectionItem.quantity.sum().coalesce(0).intValue()
                ))
                .from(seafoodCollection)
                .leftJoin(collectionItem).on(collectionItem.seafoodCollectionId.eq(seafoodCollection.id))
                .join(collectionItem.seafood, seafood)
                .where(seafoodCollection.userId.eq(userId))
                .groupBy(seafood.id)
                .fetch();
    }

    /**
     * 사용자가 수집한 해산물 목록 조회 (수집 날짜 포함)
     */
    public List<MySeafoodCollectionInfo> findCollectedSeafoodByUserId(Long userId) {
        return queryFactory
                .select(Projections.constructor(MySeafoodCollectionInfo.class,
                        seafood.id,
                        collectionItem.quantity.sum().coalesce(0).intValue(),
                        seafoodCollection.collectedAt.min()
                ))
                .from(seafoodCollection)
                .leftJoin(collectionItem).on(collectionItem.seafoodCollectionId.eq(seafoodCollection.id))
                .join(collectionItem.seafood, seafood)
                .where(seafoodCollection.userId.eq(userId))
                .groupBy(seafood.id)
                .fetch();
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
