package com.groom.sumbisori.domain.content.respoistory;

import com.groom.sumbisori.domain.content.entity.Youtube;
import java.sql.PreparedStatement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class YoutubeRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;

    public void saveAll(List<Youtube> youtubeVideos) {
        String sql = "INSERT INTO youtube (video_id, title, channel_title, thumbnail_url, view_count, publish_time) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, youtubeVideos, youtubeVideos.size(),
                (PreparedStatement ps, Youtube youtube) -> {
                    ps.setString(1, youtube.getVideoId());
                    ps.setString(2, youtube.getTitle());
                    ps.setString(3, youtube.getChannelTitle());
                    ps.setString(4, youtube.getThumbnailUrl());
                    ps.setObject(5, youtube.getViewCount());
                    ps.setObject(6, youtube.getPublishTime());
                });
    }
}
