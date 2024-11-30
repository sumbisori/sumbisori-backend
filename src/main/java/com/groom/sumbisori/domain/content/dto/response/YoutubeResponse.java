package com.groom.sumbisori.domain.content.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.SearchResult;
import com.groom.sumbisori.domain.content.entity.Youtube;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public record YoutubeResponse(
        @Schema(description = "유튜브 비디오 고유 ID", example = "KmeC-iXxuV4")
        String videoId,
        @Schema(description = "유튜브 비디오 제목", example = "제주 해녀체험 수영 못하는 나도 할 수 있다! (w.도시해녀)")
        String title,
        @Schema(description = "유튜브 비디오 썸네일 URL", example = "https://i.ytimg.com/vi/KmeC-iXxuV4/default.jpg")
        String thumbnailUrl,
        @Schema(description = "유트보 등록 날짜 (한국 시간 기준)", example = "2022-07-21T08:16:00")
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime publishTime) {
    public static YoutubeResponse from(Youtube youtube) {
        return new YoutubeResponse(
                youtube.getVideoId(),
                youtube.getTitle(),
                youtube.getThumbnailUrl(),
                youtube.getPublishTime());
    }

    public static YoutubeResponse from(SearchResult searchResult) {
        String videoId = searchResult.getId().getVideoId();
        String title = searchResult.getSnippet().getTitle();
        String thumbnailUrl = searchResult.getSnippet().getThumbnails().getDefault().getUrl();
        DateTime publishedAt = searchResult.getSnippet().getPublishedAt();
        LocalDateTime publishTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(publishedAt.getValue()),
                ZoneId.of("Asia/Seoul")
        );
        return new YoutubeResponse(videoId, title, thumbnailUrl, publishTime);
    }
}