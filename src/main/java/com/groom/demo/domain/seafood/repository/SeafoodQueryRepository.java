package com.groom.demo.domain.seafood.repository;

import static com.groom.demo.domain.collection.entity.QSeafoodCollection.seafoodCollection;
import static com.groom.demo.domain.seafood.entity.QSeafood.seafood;

import com.groom.demo.domain.seafood.dto.MySeafoodDto;
import com.groom.demo.domain.seafood.dto.QMySeafoodDto;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
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
                ConstantImpl.create("")  // null일 때 대체할 값
        );
        return queryFactory
                .select(new QMySeafoodDto(
                        seafood.id,
                        seafood.koreanName,
                        seafood.englishName,
                        seafood.description,
                        dateTemplate,
                        seafoodCollection.quantity.sum()
                ))
                .from(seafood)
                .leftJoin(seafoodCollection)
                .on(seafoodCollection.seafood.eq(seafood).and(seafoodCollection.userId.eq(userId)))
                .groupBy(seafood.id, seafood.koreanName, seafood.englishName, seafood.description)
                .fetch();
    }

}
