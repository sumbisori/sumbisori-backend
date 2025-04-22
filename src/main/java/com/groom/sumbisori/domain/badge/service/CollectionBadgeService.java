package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.dto.event.SignUpEvent;
import com.groom.sumbisori.domain.badge.entity.BadgeCode;
import com.groom.sumbisori.domain.collection.dto.event.CollectionEvent;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CollectionBadgeService {
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void awardCollectionBadge(CollectionEvent event) {
        log.info("해산물 배지 조건 확인 시작 - 사용자 ID: {}, 해산물 ID 목록: {}", event.userId(), event.seafoodIds());
//
//        // 해산물 리스트를 통해 포함된 해산물 배지 레벨 조회
//
//        // 미리사용자의 해당하는 해산물 채집 갯수를 조회해두기
//        Map<Long, Integer> userSeafoodCounts = getUserSeafoodCounts(event.userId(), event.seafoodIds());
//
//        // 미리사용자가 갖고있는 배지 레벨 조회 해두기
//        Set<BadgeCode> existingBadges = getUserExistingBadges(event.userId());
//
//        // 반복을 하면서 획득하지 못한 레벨 조건에 맞는지 찾기


    }
}
