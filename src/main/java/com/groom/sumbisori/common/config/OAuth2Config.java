package com.groom.sumbisori.common.config;

import com.groom.sumbisori.common.filter.LoggingRequestInterceptor;
import com.groom.sumbisori.common.oauth2.CustomAuthorizationCodeTokenResponseClient;
import java.net.InetSocketAddress;
import java.net.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OAuth2Config {

    @Bean
    @Profile("prod")
    public RestTemplate oAuthRestTemplateProd() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        // 프록시 설정
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("krmp-proxy.9rum.cc", 3128));
        requestFactory.setProxy(proxy);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        // 로깅 인터셉터 추가
        restTemplate.getInterceptors().add(new LoggingRequestInterceptor());

        return restTemplate;
    }

    @Bean
    @Profile("!prod")
    public RestTemplate oAuthRestTemplateNonProd() {
        RestTemplate restTemplate = new RestTemplate();

        // 로깅 인터셉터 추가
        restTemplate.getInterceptors().add(new LoggingRequestInterceptor());

        return restTemplate;
    }

    // OAuth2AccessTokenResponseClient 빈 정의 (RestTemplate 주입)
    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> oAuth2AccessTokenResponseClient(
            RestTemplate restTemplate) {
        return new CustomAuthorizationCodeTokenResponseClient(restTemplate);
    }
}
