package com.groom.sumbisori.domain.collection.repository;

import static com.groom.sumbisori.domain.collection.entity.QSeafoodCollection.seafoodCollection;
import static com.groom.sumbisori.domain.collectionitem.entity.QCollectionItem.collectionItem;
import static com.groom.sumbisori.domain.file.entity.QFile.file;
import static com.groom.sumbisori.domain.seafood.entity.QSeafood.seafood;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import com.groom.sumbisori.domain.collection.dto.response.CollectionResult;
import com.groom.sumbisori.domain.collection.dto.response.MySeafoodCollectionInfo;
import com.groom.sumbisori.domain.collection.dto.response.SeafoodCollectionInfo;
import com.groom.sumbisori.domain.file.entity.RefType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CollectionQueryRepository {
    public static final int REPRESENTATIVE_SEQUENCE = 1;

    private final JPAQueryFactory queryFactory;

    /**
     * 사용자가 채취한 해산물 목록 조회
     */
    public List<SeafoodCollectionInfo> findTotalQuantityBySeafoodForUser(Long userId) {
        return queryFactory
                .select(Projections.constructor(SeafoodCollectionInfo.class,
                        seafood.id,
                        seafood.koreanName,
                        seafood.englishName,
                        collectionItem.quantity.sum().coalesce(0).intValue()
                ))
                .from(seafoodCollection)
                .join(collectionItem).on(collectionItem.seafoodCollectionId.eq(seafoodCollection.id))
                .join(collectionItem.seafood, seafood)
                .where(seafoodCollection.userId.eq(userId))
                .groupBy(seafood.id)
                .fetch();
    }

    /**
     * 사용자가 채취한 해산물 목록 조회 (채취 날짜 포함)
     */
    public List<MySeafoodCollectionInfo> findCollectedSeafoodByUserId(Long userId) {
        return queryFactory
                .select(Projections.constructor(MySeafoodCollectionInfo.class,
                        seafood.id,
                        collectionItem.quantity.sum().coalesce(0).intValue(),
                        seafoodCollection.collectedAt.min()
                ))
                .from(seafoodCollection)
                .join(collectionItem).on(collectionItem.seafoodCollectionId.eq(seafoodCollection.id))
                .join(collectionItem.seafood, seafood)
                .where(seafoodCollection.userId.eq(userId))
                .groupBy(seafood.id)
                .fetch();
    }

    /**
     * 사용자가 채취한 해산물 ID 목록 조회
     */
    public List<Long> findDistinctCollectedSeafoodIds(Long userId) {
        return queryFactory
                .select(seafood.id).distinct()
                .from(seafoodCollection)
                .join(collectionItem).on(collectionItem.seafoodCollectionId.eq(seafoodCollection.id))
                .join(collectionItem.seafood, seafood)
                .where(seafoodCollection.userId.eq(userId))
                .fetch();
    }

    /**
     * 특정 체험에 대한 전체 해산물 채취 현황 조회
     */
    public List<SeafoodCollectionInfo> findStatisticsByExperienceId(Long experienceId) {
        return queryFactory
                .select(Projections.constructor(SeafoodCollectionInfo.class,
                        seafood.id,
                        seafood.koreanName,
                        seafood.englishName,
                        collectionItem.quantity.sum().coalesce(0).intValue()
                ))
                .from(seafoodCollection)
                .join(collectionItem).on(collectionItem.seafoodCollectionId.eq(seafoodCollection.id))
                .join(collectionItem.seafood, seafood)
                .where(seafoodCollection.experienceId.eq(experienceId))
                .groupBy(seafood.id)
                .orderBy(seafood.id.asc())
                .fetch();
    }

    public List<CollectionResult> findCollectedByExperienceId(Long experienceId) {
        return queryFactory
                .from(seafoodCollection)
                .where(seafoodCollection.experienceId.eq(experienceId))
                .leftJoin(file).on(file.refId.eq(seafoodCollection.id)
                        .and(file.refType.eq(RefType.SEAFOOD_COLLECTION))
                        .and(file.sequence.eq(REPRESENTATIVE_SEQUENCE)))
                .join(collectionItem).on(collectionItem.seafoodCollectionId.eq(seafoodCollection.id))
                .join(seafood).on(collectionItem.seafood.eq(seafood))
                .transform(
                        groupBy(seafoodCollection.id).list(
                                Projections.constructor(
                                        CollectionResult.class,
                                        file.imageIdentifier,
                                        list(
                                                Projections.constructor(
                                                        SeafoodCollectionInfo.class,
                                                        seafood.id,
                                                        seafood.koreanName,
                                                        seafood.englishName,
                                                        collectionItem.quantity
                                                )
                                        )
                                )
                        )
                );
    }

    /**
     * 특정 해산물을 수집한 총 개수를 계산
     **/
    public int countTotalQuantityByUserIdAndSeafoodId(Long userId, Long seafoodId) {
        return queryFactory
                .select(collectionItem.quantity.sum().coalesce(0).intValue())
                .from(seafoodCollection)
                .join(collectionItem).on(collectionItem.seafoodCollectionId.eq(seafoodCollection.id))
                .where(seafoodCollection.userId.eq(userId)
                        .and(collectionItem.seafood.id.eq(seafoodId)))
                .fetchOne();
    }

//    private BooleanBuilder seafoodCollectionUserIdEq(Long userId) {
//        return nullSafeBuilder(() -> seafoodCollection.userId.eq(userId));
//    }
}
