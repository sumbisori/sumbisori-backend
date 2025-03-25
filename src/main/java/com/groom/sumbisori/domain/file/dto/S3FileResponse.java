package com.groom.sumbisori.domain.file.dto;

public record S3FileResponse(byte[] data,
                             String contentType) {
}
