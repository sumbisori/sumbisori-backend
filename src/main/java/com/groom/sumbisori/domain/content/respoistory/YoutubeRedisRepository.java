package com.groom.sumbisori.domain.content.respoistory;

import com.groom.sumbisori.domain.content.dto.response.YoutubeResponse;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class YoutubeRedisRepository {
    public static final String YOUTUBE_KEY = "youtube:videos";

    private final RedisTemplate<String, YoutubeResponse> redisTemplate;

    public void saveYoutubeVideosToCache(List<YoutubeResponse> youtubeResponses) {
        BoundSetOperations<String, YoutubeResponse> setOps = redisTemplate.boundSetOps(YOUTUBE_KEY);
        youtubeResponses.forEach(setOps::add);
        redisTemplate.expire(YOUTUBE_KEY, Duration.ofDays(1));
    }

    public Set<YoutubeResponse> findRandomVideosFromCache(int count) {
        return redisTemplate.opsForSet().distinctRandomMembers(YOUTUBE_KEY, count);
    }
}
