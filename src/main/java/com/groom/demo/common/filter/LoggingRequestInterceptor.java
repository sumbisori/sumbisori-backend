package com.groom.demo.common.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

@Slf4j
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        // 응답을 래핑하여 여러 번 읽을 수 있도록 합니다.
        ClientHttpResponse responseCopy = new BufferingClientHttpResponseWrapper(response);
        logResponse(responseCopy);
        return responseCopy;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        log.debug("===== Request Begin =====");
        log.debug("URI         : {}", request.getURI());
        log.debug("Method      : {}", request.getMethod());
        log.debug("Headers     : {}", request.getHeaders());
        log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
        log.debug("===== Request End =====");
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
        log.debug("===== Response Begin =====");
        log.debug("Status code  : {}", response.getStatusCode());
        log.debug("Status text  : {}", response.getStatusText());
        log.debug("Headers      : {}", response.getHeaders());
        log.debug("Response body: {}", responseBody);
        log.debug("===== Response End =====");
    }
}
