package com.groom.sumbisori.domain.seafood.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeafoodQueryRepository {
    private final JPAQueryFactory queryFactory;
}
