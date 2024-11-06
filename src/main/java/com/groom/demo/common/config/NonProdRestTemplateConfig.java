package com.groom.demo.common.config;

import com.groom.demo.common.filter.LoggingRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("!prod")  // 프로덕션 환경이 아닐 때 활성화
public class NonProdRestTemplateConfig {

    @Bean
    public RestTemplate oAuthRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // 로깅 인터셉터 추가
        restTemplate.getInterceptors().add(new LoggingRequestInterceptor());

        return restTemplate;
    }
}
