package com.groom.sumbisori.domain.collection.repository;

import com.groom.sumbisori.domain.collection.dto.response.MySeafoodCollectionInfo;
import com.groom.sumbisori.domain.collection.entity.SeafoodCollection;
import com.groom.sumbisori.domain.collectionitem.entity.CollectionItem;
import com.groom.sumbisori.domain.seafood.entity.Seafood;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CollectionQueryRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    private CollectionQueryRepository collectionQueryRepository;

    @BeforeEach
    void setUp() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        collectionQueryRepository = new CollectionQueryRepository(queryFactory);

        Seafood seafood1 = Seafood.builder()
                .koreanName("전복")
                .englishName("Abalone")
                .description("전복은 바다에서 자생하는 조개류로, 단백질과 미네랄이 풍부하다.")
                .build();
        Seafood seafood2 = Seafood.builder()
                .koreanName("조개")
                .englishName("Clam")
                .description("조개는 바다와 강에서 자생하는 조개류로, 단백질과 비타민이 풍부하다.")
                .build();
        em.persist(seafood1);
        em.persist(seafood2);

        SeafoodCollection seafoodCollection1 = SeafoodCollection.builder()
                .userId(1L)
                .experienceId(1L)
                .collectedAt(LocalDate.parse("2023-10-01"))
                .build();

        SeafoodCollection seafoodCollection2 = SeafoodCollection.builder()
                .userId(1L)
                .experienceId(2L)
                .collectedAt(LocalDate.parse("2023-10-02"))
                .build();

        em.persist(seafoodCollection1);
        em.persist(seafoodCollection2);

        CollectionItem collectionItem1 = CollectionItem.builder()
                .seafood(seafood1)
                .seafoodCollectionId(seafoodCollection1.getId())
                .quantity(5)
                .build();

        CollectionItem collectionItem2 = CollectionItem.builder()
                .seafood(seafood2)
                .seafoodCollectionId(seafoodCollection1.getId())
                .quantity(3)
                .build();

        CollectionItem collectionItem3 = CollectionItem.builder()
                .seafood(seafood1)
                .seafoodCollectionId(seafoodCollection2.getId())
                .quantity(8)
                .build();

        em.persist(collectionItem1);
        em.persist(collectionItem2);
        em.persist(collectionItem3);

        em.flush();
        em.clear();
    }

    @Test
    void findCollectedSeafoodByUserId_사용자_채집량_조회() {
        // Given
        Long userId = 1L;

        // When
        List<MySeafoodCollectionInfo> result = collectionQueryRepository.findCollectedSeafoodByUserId(userId);

        // Then
        assertThat(result).hasSize(2); // 전복(1L), 조개(2L) -> 2개의 해산물

        // 전복 데이터 검증 (ID=1, SUM=5+8=13)
        MySeafoodCollectionInfo seafood1 = result.stream()
                .filter(s -> s.seafoodId().equals(1L))
                .findFirst()
                .orElseThrow();
        assertThat(seafood1.seafoodId()).isEqualTo(1L);
        assertThat(seafood1.count()).isEqualTo(13); // 5 + 8 = 13
        assertThat(seafood1.collectedAt()).isEqualTo(LocalDate.parse("2023-10-01")); // 가장 이른 수집 날짜

        // 조개 데이터 검증 (ID=2, SUM=3)
        MySeafoodCollectionInfo seafood2 = result.stream()
                .filter(s -> s.seafoodId().equals(2L))
                .findFirst()
                .orElseThrow();
        assertThat(seafood2.seafoodId()).isEqualTo(2L);
        assertThat(seafood2.count()).isEqualTo(3);
        assertThat(seafood1.collectedAt()).isEqualTo(LocalDate.parse("2023-10-01"));
    }

    @Test
    void findCollectedSeafoodByUserId_사용자가_채집한_해산물이_없을때_빈리스트_반환() {
        // Given
        Long nonExistingUserId = 999L;

        // When
        List<MySeafoodCollectionInfo> result = collectionQueryRepository.findCollectedSeafoodByUserId(
                nonExistingUserId);

        // Then
        assertThat(result).isEmpty();
    }
}
