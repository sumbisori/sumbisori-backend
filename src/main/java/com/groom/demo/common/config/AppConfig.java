package com.groom.demo.common.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    @Profile("prod")
    public RestTemplate proxiedRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("krmp-proxy.9rum.cc", 3128));
        requestFactory.setProxy(proxy);
        return new RestTemplate(requestFactory);
    }

    @Bean
    @Profile("!prod")
    public RestTemplate defaultRestTemplate() {
        return new RestTemplate();
    }
}
