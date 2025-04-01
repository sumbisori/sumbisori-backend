package com.groom.sumbisori.domain.collection.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.domain.collection.dto.response.SeafoodCollectionInfo;
import com.groom.sumbisori.domain.collection.dto.response.MySeafoodCollectionStatus;
import com.groom.sumbisori.domain.collection.service.CollectionLookupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
public class CollectionController implements CollectionApi {
    private final CollectionLookupService collectionLookupService;

    /**
     * 나의 수집 해산물 조회
     */
    @GetMapping
    public ResponseEntity<List<SeafoodCollectionInfo>> getMySeafoodCollection(@LoginUser Long userId) {
        return ResponseEntity.ok(collectionLookupService.getMySeafoodCollection(userId));
    }

    /**
     * 수집 현황 조회
     */
    @GetMapping("/status")
    public ResponseEntity<List<MySeafoodCollectionStatus>> getSeafoodCollectionStatus(@LoginUser Long userId) {
        return ResponseEntity.ok(collectionLookupService.getSeafoodCollectionStatus(userId));
    }
}
