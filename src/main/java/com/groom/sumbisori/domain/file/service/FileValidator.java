package com.groom.sumbisori.domain.file.service;

import com.groom.sumbisori.domain.file.error.FileErrorcode;
import com.groom.sumbisori.domain.file.error.FileException;
import java.util.List;

public class FileValidator {
    static final List<String> ImageContentTypes = List.of(
            "image/jpeg",
            "image/png",
            "image/gif"
    );

    public static void validateImageContentType(String contentType) {
        if (!ImageContentTypes.contains(contentType)) {
            throw new FileException(FileErrorcode.INVALID_IMAGE_CONTENT_TYPE);
        }
    }
}
