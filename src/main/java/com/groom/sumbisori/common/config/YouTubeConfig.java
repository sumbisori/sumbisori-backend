package com.groom.sumbisori.common.config;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YouTubeConfig {
    private static final String APPLICATION_NAME = "sumbisori";

    // YouTube 클라이언트 생성 메서드
    @Bean
    public YouTube youTubeClient() {
        return new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), request -> {})
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
