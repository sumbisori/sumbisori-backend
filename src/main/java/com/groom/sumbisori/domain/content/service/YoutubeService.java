package com.groom.sumbisori.domain.content.service;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube.Search;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.groom.sumbisori.domain.content.dto.response.YoutubeResponse;
import com.groom.sumbisori.domain.content.respoistory.YoutubeRedisRepository;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class YoutubeService {
    private static final String APPLICATION_NAME = "sumbisori";
    private static final String SEARCH_PART = "id,snippet";
    private static final String SEARCH_FIELDS = "items(id,snippet(title,thumbnails(default),publishedAt))";
    private static final String SEARCH_QUERY = "제주도 해녀체험";
    private static final String SEARCH_TYPE = "video";
    private static final boolean EMBEDDABLE = true;
    private static final long MAX_RESULTS = 50L;

    private final YoutubeRedisRepository youtubeRedisRepository;


    @Value("${youtube.api-key}")
    private String apiKey;

    @Scheduled(fixedRate = 3600000) // 1시간마다 실행 (밀리초 기준)
    public void saveYoutubeCache() {
        List<SearchResult> youtubeList = getYoutubeList();
        if (!youtubeList.isEmpty()) {
            List<YoutubeResponse> youtubeResponses = youtubeList.stream()
                    .map(YoutubeResponse::from)
                    .collect(Collectors.toList());
            youtubeRedisRepository.saveYoutubeVideosToCache(youtubeResponses);
            log.info("YouTube API에서 데이터를 가져와 캐시에 저장했습니다.");
        } else {
            log.error("YouTube API에서 데이터를 가져오지 못했습니다.");
        }
    }


    private List<SearchResult> getYoutubeList() {
        YouTube youtube = createYouTubeClient();
        try {
            // YouTube Search API 요청 생성
            Search.List search = youtube.search()
                    .list(Collections.singletonList(SEARCH_PART))
                    .setKey(apiKey)
                    .setType(Collections.singletonList(SEARCH_TYPE))
                    .setQ(SEARCH_QUERY)
                    .setMaxResults(MAX_RESULTS)
                    .setFields(SEARCH_FIELDS)
                    .setVideoEmbeddable(String.valueOf(EMBEDDABLE));

            // 요청 실행 및 결과 반환
            SearchListResponse searchResponse = search.execute();
            return searchResponse.getItems();
        } catch (IOException e) {
            log.error("YouTube API 요청 중 오류 발생", e);
        }

        // 요청 실패 시 빈 리스트 반환
        return Collections.emptyList();
    }

    // YouTube 클라이언트 생성 메서드
    private YouTube createYouTubeClient() {
        return new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), request -> {
        })
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
