package com.groom.sumbisori.domain.content.service;

import com.groom.sumbisori.domain.content.dto.response.YoutubeResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YoutubeRandomPicker {
    private final YoutubeLookupService youtubeLookupService;
    private final Random random = new Random();

    /**
     * 캐시에 저장된 Youtube 동영상 목록을 랜덤으로 count개 조회
     */
    public List<YoutubeResponse> lookup(int count) {
        List<YoutubeResponse> videos = new ArrayList<>(youtubeLookupService.getVideos());
        for (int i = videos.size() - 1; i >= videos.size() - count; i--) {
            int j = random.nextInt(i + 1);
            Collections.swap(videos, i, j);
        }
        return videos.subList(videos.size() - count, videos.size());
    }
}
