package com.groom.sumbisori.domain.content.service;

import com.groom.sumbisori.domain.content.dto.response.YoutubeResponse;
import com.groom.sumbisori.domain.content.respoistory.YoutubeRepository;
import com.groom.sumbisori.domain.content.respoistory.YoutubeRepositoryImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class YoutubeUpdateService {
    private final YoutubeRepository youtubeRepository;
    private final YoutubeRepositoryImpl youtubeRepositoryImpl;

    @Transactional
    public void update(List<YoutubeResponse> youtubeResponses) {
        youtubeRepository.deleteAllInBatch();
        youtubeRepositoryImpl.saveAll(youtubeResponses.stream()
                .map(YoutubeResponse::toEntity)
                .toList());
    }

}
