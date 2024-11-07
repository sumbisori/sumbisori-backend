package com.groom.sumbisori.common.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

    private final ClientHttpResponse response;
    private byte[] body;

    public BufferingClientHttpResponseWrapper(ClientHttpResponse response) throws IOException {
        this.response = response;
        // Response body를 바이트 배열로 복사합니다.
        InputStream responseStream = response.getBody();
        this.body = StreamUtils.copyToByteArray(responseStream);
    }

    @Override
    public HttpStatusCode getStatusCode() throws IOException {
        // 원본 응답의 상태 코드를 반환합니다.
        return this.response.getStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
        // 원본 응답의 상태 텍스트를 반환합니다.
        return this.response.getStatusText();
    }

    @Override
    public void close() {
        // 원본 응답을 닫습니다.
        this.response.close();
    }

    @Override
    public InputStream getBody() throws IOException {
        // 바이트 배열로부터 새로운 InputStream을 생성하여 반환합니다.
        return new ByteArrayInputStream(this.body);
    }

    @Override
    public HttpHeaders getHeaders() {
        // 원본 응답의 헤더를 반환합니다.
        return this.response.getHeaders();
    }
}
