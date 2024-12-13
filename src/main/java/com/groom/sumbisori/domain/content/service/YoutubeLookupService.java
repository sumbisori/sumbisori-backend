package com.groom.sumbisori.domain.content.service;

import com.groom.sumbisori.domain.content.dto.response.YoutubeResponse;
import com.groom.sumbisori.domain.content.respoistory.YoutubeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class YoutubeLookupService {
    private final YoutubeRepository youtubeRepository;

    @Cacheable(value = "youtubes")
    public List<YoutubeResponse> getVideos() {
        return youtubeRepository.findAll().stream()
                .map(YoutubeResponse::from)
                .toList();
    }
}
