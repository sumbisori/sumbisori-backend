package com.groom.sumbisori.domain.experience.repository;

import static com.groom.sumbisori.domain.experience.domain.QExperience.experience;
import static com.groom.sumbisori.domain.file.entity.QFile.file;
import static com.groom.sumbisori.domain.place.entity.QPlace.place;

import com.groom.sumbisori.domain.experience.dto.ExperienceDetailQueryDto;
import com.groom.sumbisori.domain.experience.dto.ExperienceQueryDto;
import com.groom.sumbisori.domain.file.entity.RefType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExperienceQueryRepository {
    public static final int REPRESENTATIVE_SEQUENCE = 1;

    private final JPAQueryFactory queryFactory;

    public Page<ExperienceQueryDto> findByUserId(Long userId, Pageable pageable) {
        List<ExperienceQueryDto> content = queryFactory
                .select(Projections.constructor(ExperienceQueryDto.class,
                        experience.id,
                        experience.experienceDate,
                        experience.companionType,
                        experience.weather,
                        place.name,
                        file.imageIdentifier)
                )
                .from(experience)
                .where(experience.userId.eq(userId))
                .join(place).on(place.eq(experience.place))
                .leftJoin(file).on(file.refId.eq(experience.id)
                        .and(file.refType.eq(RefType.EXPERIENCE))
                        .and(file.sequence.eq(REPRESENTATIVE_SEQUENCE)))
                .orderBy(experience.experienceDate.desc(), experience.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(experience.count())
                .from(experience)
                .where(experience.userId.eq(userId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    public Optional<ExperienceDetailQueryDto> findByExperienceId(Long userId, Long experienceId) {
        Optional<ExperienceDetailQueryDto> result = Optional.ofNullable(queryFactory
                .select(Projections.constructor(ExperienceDetailQueryDto.class,
                        experience.id,
                        experience.experienceDate,
                        experience.satisfaction,
                        experience.companionType,
                        experience.weather,
                        experience.impression,
                        experience.createdAt,
                        place.name)
                )
                .from(experience)
                .where(experience.id.eq(experienceId).and(experience.userId.eq(userId)))
                .join(place).on(place.eq(experience.place))
                .fetchOne());

        result.ifPresent(response -> response.addImageIdentifiers(
                getImageIdentifiers(response.getExperienceId())
        ));

        return result;
    }

    private List<String> getImageIdentifiers(Long experienceId) {
        return queryFactory
                .select(file.imageIdentifier)
                .from(file)
                .where(file.refId.eq(experienceId).and(file.refType.eq(RefType.EXPERIENCE)))
                .orderBy(file.sequence.asc())
                .fetch();
    }
}
