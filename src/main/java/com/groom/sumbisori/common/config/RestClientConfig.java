package com.groom.sumbisori.common.config;

import java.time.Duration;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    private static final int CONNECT_TIMEOUT_SECONDS = 5;
    private static final int READ_TIMEOUT_SECONDS = 5;

    @Bean
    RestClient restClient() {
        return RestClient.builder()
                .requestFactory(customRequestFactory())
                .build();
    }

    private ClientHttpRequestFactory customRequestFactory() {
        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(Duration.ofSeconds(CONNECT_TIMEOUT_SECONDS))
                .withReadTimeout(Duration.ofSeconds(READ_TIMEOUT_SECONDS));
        return ClientHttpRequestFactories.get(settings);
    }
}
