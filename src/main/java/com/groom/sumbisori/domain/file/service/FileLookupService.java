package com.groom.sumbisori.domain.file.service;

import com.groom.sumbisori.domain.file.dto.S3FileResponse;
import com.groom.sumbisori.domain.file.entity.File;
import com.groom.sumbisori.domain.file.error.FileErrorcode;
import com.groom.sumbisori.domain.file.error.FileException;
import com.groom.sumbisori.domain.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileLookupService {
    private static final String AFTER_PREFIX = "private/experience/";

    private final S3Util s3Util;
    private final FileRepository fileRepository;

    @Transactional(readOnly = true)
    public S3FileResponse lookup(Long userId, String imageIdentifier) {
        File file = fileRepository.findByImageIdentifier(imageIdentifier)
                .orElseThrow(() -> new FileException(FileErrorcode.FILE_NOT_FOUND));
        if (!file.isOwner(userId)) {
            throw new FileException(FileErrorcode.INVALID_PERMISSION);
        }
        return s3Util.downloadFileAsBytes(AFTER_PREFIX + file.getImageIdentifier());
    }
}
