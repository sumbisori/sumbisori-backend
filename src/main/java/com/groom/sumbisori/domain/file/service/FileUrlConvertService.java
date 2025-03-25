package com.groom.sumbisori.domain.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUrlConvertService {
    private static final String FILE_API_URL_PREFIX = "/api/files/";
    private static String serverUrl;

    @Value("${server.url}")
    public void setServerUrl(String serverUrl) {
        FileUrlConvertService.serverUrl = serverUrl;
    }

    public static String convert(String imageIdentifier) {
        return serverUrl + FILE_API_URL_PREFIX + imageIdentifier;
    }

}
