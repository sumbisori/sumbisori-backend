package com.groom.sumbisori.domain.content.service;

import com.groom.sumbisori.domain.content.dto.response.YoutubeResponse;
import com.groom.sumbisori.domain.content.entity.Youtube;
import com.groom.sumbisori.domain.content.respoistory.YoutubeRedisRepository;
import com.groom.sumbisori.domain.content.respoistory.YoutubeRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YoutubeLookupService {
    private final YoutubeRedisRepository youtubeRedisRepository;
    private final YoutubeRepository youtubeRepository;

    /**
     * Redis Cache에 저장된 Youtube 동영상 목록을 랜덤으로 count개 조회, 없으면 데이터베이스 조회
     * Youtube 동영상을 목록을 조회
     */
    public Set<YoutubeResponse> lookup(int count) {
        Set<YoutubeResponse> youtubeResponses = youtubeRedisRepository.findRandomVideosFromCache(count);
        if (youtubeResponses.isEmpty() || youtubeResponses == null) {
            List<Youtube> youtubeVideos = youtubeRepository.findRandomList(count);
            return youtubeVideos.stream()
                    .map(YoutubeResponse::from)
                    .collect(Collectors.toSet());
        }
        return youtubeResponses;
    }
}
