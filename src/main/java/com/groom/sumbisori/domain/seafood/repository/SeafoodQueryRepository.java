package com.groom.sumbisori.domain.seafood.repository;

import static com.groom.sumbisori.common.util.QuerydslUtil.nullSafeBuilder;
import static com.groom.sumbisori.domain.collection.entity.QSeafoodCollection.seafoodCollection;
import static com.groom.sumbisori.domain.seafood.entity.QSeafood.seafood;

import com.groom.sumbisori.domain.seafood.dto.MySeafoodDto;
import com.groom.sumbisori.domain.seafood.dto.QMySeafoodDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeafoodQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<MySeafoodDto> findAllSeafoodCollectionStatusByUserId(Long userId) {
        DateTemplate<String> dateTemplate = Expressions.dateTemplate(
                String.class,
                "coalesce(DATE_FORMAT(MIN({0}), {1}), {2})",
                seafoodCollection.createdAt,
                ConstantImpl.create("%Y년 %m월 %d일"),
                ConstantImpl.create("")
        );

        NumberExpression<Integer> quantitySum = seafoodCollection.quantity.sum();
        StringExpression descriptionExpression = new CaseBuilder()
                .when(quantitySum.isNull().or(quantitySum.eq(0)))
                .then("아직 수집하지 못했어요")
                .otherwise(seafood.description);

        return queryFactory
                .select(new QMySeafoodDto(
                        seafood.id,
                        seafood.koreanName,
                        seafood.englishName,
                        descriptionExpression,
                        dateTemplate,
                        quantitySum
                ))
                .from(seafood)
                .leftJoin(seafoodCollection)
                .on(seafoodCollection.seafood.eq(seafood).and(seafoodCollectionUserIdEq(userId)))
                .groupBy(
                        seafood.id,
                        seafood.koreanName,
                        seafood.englishName
                )
                .fetch();
    }

    private BooleanBuilder seafoodCollectionUserIdEq(Long userId) {
        return nullSafeBuilder(() -> seafoodCollection.userId.eq(userId));
    }

}
