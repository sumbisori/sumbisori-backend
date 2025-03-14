package com.groom.sumbisori.domain.collection.dto.response;

import java.time.LocalDate;

public record MySeafoodCollectionInfo(Long seafoodId, int count, LocalDate collectedAt) {
}
