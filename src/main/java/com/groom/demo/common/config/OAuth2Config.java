package com.groom.demo.common.config;

import com.groom.demo.common.filter.LoggingRequestInterceptor;
import java.net.InetSocketAddress;
import java.net.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OAuth2Config {

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

    // Spring Security OAuth2 클라이언트가 액세스 토큰을 요청할 때 프록시 설정 등 커스텀 RestTemplate 설정을 적용
    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> oAuth2AccessTokenResponseClient(
            RestTemplate oAuthRestTemplate) {
        DefaultAuthorizationCodeTokenResponseClient client = new DefaultAuthorizationCodeTokenResponseClient();
        client.setRestOperations(oAuthRestTemplate);
        return client;
    }
}
