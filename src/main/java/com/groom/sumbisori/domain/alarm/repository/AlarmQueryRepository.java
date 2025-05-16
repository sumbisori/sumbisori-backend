package com.groom.sumbisori.domain.alarm.repository;

import static com.groom.sumbisori.domain.alarm.entity.QAlarm.alarm;

import com.groom.sumbisori.domain.alarm.dto.response.AlarmResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AlarmQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<AlarmResponse> findAlarmsByUserId(final Long userId, final Pageable pageable) {
        List<AlarmResponse> content = queryFactory
                .select(
                        Projections.constructor(
                                AlarmResponse.class,
                                alarm.id,
                                alarm.type,
                                alarm.content,
                                alarm.link,
                                alarm.isRead,
                                alarm.createdAt
                        )
                )
                .from(alarm)
                .where(alarm.userId.eq(userId).and(alarm.isDeleted.isFalse()))
                .orderBy(alarm.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(alarm.count())
                .from(alarm)
                .where(alarm.userId.eq(userId).and(alarm.isDeleted.isFalse()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
