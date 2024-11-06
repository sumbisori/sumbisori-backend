package com.groom.demo.common.config;

import com.groom.demo.common.filter.LoggingRequestInterceptor;
import java.net.InetSocketAddress;
import java.net.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("prod")  // 프로덕션 환경에서만 활성화
public class ProdRestTemplateConfig {

    @Bean
    public RestTemplate oAuthRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("krmp-proxy.9rum.cc", 3128));
        requestFactory.setProxy(proxy);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        // 로깅 인터셉터 추가
        restTemplate.getInterceptors().add(new LoggingRequestInterceptor());

        return restTemplate;
    }
}
