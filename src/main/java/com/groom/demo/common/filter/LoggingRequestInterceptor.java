package com.groom.demo.common.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@Slf4j
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        return logResponse(response);
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        log.debug("===== Request Begin =====");
        log.debug("URI         : {}", request.getURI());
        log.debug("Method      : {}", request.getMethod());
        log.debug("Headers     : {}", request.getHeaders());
        log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
        log.debug("===== Request End =====");
    }

    private ClientHttpResponse logResponse(ClientHttpResponse response) throws IOException {
        ClientHttpResponse responseCopy = new BufferingClientHttpResponseWrapper(response);

        String responseBody = new BufferedReader(
                new InputStreamReader(responseCopy.getBody(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        log.debug("===== Response Begin =====");
        log.debug("Status code  : {}", responseCopy.getStatusCode());
        log.debug("Status text  : {}", responseCopy.getStatusText());
        log.debug("Headers      : {}", responseCopy.getHeaders());
        log.debug("Response body: {}", responseBody);
        log.debug("===== Response End =====");

        return responseCopy;
    }
}
