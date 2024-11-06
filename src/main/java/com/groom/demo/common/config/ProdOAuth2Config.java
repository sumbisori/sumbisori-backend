package com.groom.demo.common.config;

import com.groom.demo.common.filter.LoggingRequestInterceptor;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("prod")
public class ProdOAuth2Config {

    @Bean
    public RestTemplate oAuthRestTemplate() {
        // 프록시 설정
        HttpHost proxy = new HttpHost("krmp-proxy.9rum.cc", 3128);

        // 프록시 설정이 적용된 HttpClient 생성
        CloseableHttpClient httpClient = HttpClients.custom()
                .setProxy(proxy)
                .build();

        // 커스텀 HttpClient를 사용하는 RequestFactory 생성
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getInterceptors().add(new LoggingRequestInterceptor());
        return restTemplate;
    }
}
