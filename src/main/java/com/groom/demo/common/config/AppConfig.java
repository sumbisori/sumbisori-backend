package com.groom.demo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class AppConfig {

    // prod 프로파일에서 프록시가 적용된 RestTemplate 빈 정의
    @Bean
    @Profile("prod")
    public RestTemplate restTemplateWithProxy() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("krmp-proxy.9rum.cc", 3128));
        factory.setProxy(proxy);
        return new RestTemplate(factory);
    }

    // 기본 RestTemplate (프록시 없음) - prod가 아닌 다른 프로파일에서 사용
    @Bean
    @Profile("!prod")
    public RestTemplate defaultRestTemplate() {
        return new RestTemplate();
    }
}
