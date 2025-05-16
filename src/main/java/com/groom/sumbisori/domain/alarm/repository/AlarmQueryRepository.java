package com.groom.sumbisori.domain.alarm.repository;

import static com.groom.sumbisori.domain.alarm.entity.QAlarm.*;

import com.groom.sumbisori.domain.alarm.dto.response.AlarmResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AlarmQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<AlarmResponse> findAlarmsByUserId(final Long userId) {
        return queryFactory
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
                .where(alarm.userId.eq(userId))
                .orderBy(alarm.createdAt.desc())
                .fetch();
    }
}
