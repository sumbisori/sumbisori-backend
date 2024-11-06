package com.groom.demo.common.config;

import com.groom.demo.common.filter.LoggingRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("!prod")  // prod 프로필이 아닌 경우
public class NonProdOAuth2Config {
    @Bean
    public RestTemplate oAuthRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new LoggingRequestInterceptor());
        return restTemplate;
    }
}
