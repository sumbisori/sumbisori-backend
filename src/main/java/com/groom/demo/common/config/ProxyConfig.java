package com.groom.demo.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class ProxyConfig {

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        // spring.profiles.active가 'prod'일 때만 프록시 설정을 적용
        if ("prod".equals(activeProfile)) {
            String proxyHost = "krmp-proxy.9rum.cc";
            int proxyPort = 3128;
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            requestFactory.setProxy(proxy);
        }

        return new RestTemplate(requestFactory);
    }
}
