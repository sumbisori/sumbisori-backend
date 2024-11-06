package com.groom.demo.common.oauth2;

import com.groom.demo.domain.user.oauth2.CustomAuthorizationCodeGrantRequestEntityConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.util.Assert;
import org.springframework.web.client.RestOperations;

public class CustomAuthorizationCodeTokenResponseClient implements
        OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

    private RestOperations restOperations;
    private Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> requestEntityConverter = new CustomAuthorizationCodeGrantRequestEntityConverter();

    public CustomAuthorizationCodeTokenResponseClient(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
        Assert.notNull(authorizationGrantRequest, "authorizationGrantRequest cannot be null");

        // 올바른 컨버터 사용
        RequestEntity<?> request = requestEntityConverter.convert(authorizationGrantRequest);

        ResponseEntity<Map<String, Object>> response = restOperations.exchange(
                request, new ParameterizedTypeReference<Map<String, Object>>() {
                });

        Map<String, Object> tokenResponseParameters = response.getBody();

        return this.convertToOAuth2AccessTokenResponse(tokenResponseParameters);
    }

    private OAuth2AccessTokenResponse convertToOAuth2AccessTokenResponse(Map<String, Object> tokenResponseParameters) {
        String accessToken = (String) tokenResponseParameters.get("access_token");
        String tokenType = (String) tokenResponseParameters.get("token_type");
        Long expiresIn = getLongValue(tokenResponseParameters.get("expires_in"));
        Set<String> scopes = parseScopes(tokenResponseParameters.get("scope"));
        String refreshToken = (String) tokenResponseParameters.get("refresh_token");

        if (accessToken == null || tokenType == null) {
            throw new OAuth2AuthenticationException(new OAuth2Error("invalid_token_response"),
                    "Invalid token response");
        }

        OAuth2AccessToken.TokenType accessTokenType = OAuth2AccessToken.TokenType.BEARER;

        OAuth2AccessTokenResponse.Builder builder = OAuth2AccessTokenResponse.withToken(accessToken)
                .tokenType(accessTokenType)
                .expiresIn(expiresIn != null ? expiresIn : 0)
                .scopes(scopes);

        if (refreshToken != null) {
            builder.refreshToken(refreshToken);
        }

        return builder.build();
    }

    private Set<String> parseScopes(Object scopeObj) {
        if (scopeObj == null) {
            return Collections.emptySet();
        }
        String scope = scopeObj.toString();
        return new HashSet<>(Arrays.asList(scope.split(" ")));
    }

    private Long getLongValue(Object value) {
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        if (value instanceof String) {
            try {
                return Long.parseLong((String) value);
            } catch (NumberFormatException ex) {
                return null;
            }
        }
        return null;
    }
}
