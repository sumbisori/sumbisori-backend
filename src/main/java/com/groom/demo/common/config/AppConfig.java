package com.groom.demo.common.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final LoggingInterceptor loggingInterceptor;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        // prod 프로파일일 경우 프록시 설정
        if ("prod".equals(activeProfile)) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("krmp-proxy.9rum.cc", 3128));
            requestFactory.setProxy(proxy);
        }

        // LoggingInterceptor를 추가하여 모든 요청 및 응답을 로깅
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setInterceptors(Collections.singletonList(loggingInterceptor));

        return restTemplate;
    }
}
