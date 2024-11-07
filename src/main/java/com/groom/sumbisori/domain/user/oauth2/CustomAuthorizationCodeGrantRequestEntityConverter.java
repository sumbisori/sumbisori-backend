package com.groom.sumbisori.domain.user.oauth2;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class CustomAuthorizationCodeGrantRequestEntityConverter implements
        Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> {

    private final OAuth2AuthorizationCodeGrantRequestEntityConverter defaultConverter = new OAuth2AuthorizationCodeGrantRequestEntityConverter();

    @Override
    public RequestEntity<?> convert(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
        RequestEntity<?> request = defaultConverter.convert(authorizationGrantRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.putAll(request.getHeaders());

        MultiValueMap<String, String> formParameters = new LinkedMultiValueMap<>();
        formParameters.putAll((MultiValueMap<String, String>) request.getBody());

        // client_secret 추가
        formParameters.add("client_secret", authorizationGrantRequest.getClientRegistration().getClientSecret());

        return new RequestEntity<>(formParameters, headers, request.getMethod(), request.getUrl());
    }
}
