package com.groom.sumbisori.domain.file.service;

import com.groom.sumbisori.domain.file.dto.request.FileRequest;
import com.groom.sumbisori.domain.file.entity.File;
import com.groom.sumbisori.domain.file.entity.RefType;
import com.groom.sumbisori.domain.file.error.FileErrorcode;
import com.groom.sumbisori.domain.file.error.FileException;
import com.groom.sumbisori.domain.file.repository.FileRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileImageCreateService {
    private final FileRepository fileRepository;
    private final FileCopyService fileCopyService;

    /**
     * 다중 이미지 업로드 (sequence 필요)
     */
    @Transactional
    public void uploadMultipleImages(List<FileRequest> files, Long userId, RefType refType, Long refId) {
        validateSequence(files);

        List<File> fileEntities = files.stream()
                .map(f -> {
                    String imageIdentifier = f.imageIdentifier();
                    fileCopyService.copy(imageIdentifier);
                    return File.builder()
                            .imageIdentifier(imageIdentifier)
                            .userId(userId)
                            .refType(refType)
                            .refId(refId)
                            .sequence(f.sequence())
                            .build();
                })
                .toList();

        fileRepository.saveAll(fileEntities);
        log.info("다중 이미지 저장 완료: refType={}, refId={}, count={}", refType, refId, fileEntities.size());
    }

    /**
     * 단일 이미지 업로드 (sequence = 0)
     */
    @Transactional
    public void uploadSingleImage(String imageIdentifier, Long userId, RefType refType, Long refId) {
        fileCopyService.copy(imageIdentifier);

        File file = File.builder()
                .imageIdentifier(imageIdentifier)
                .userId(userId)
                .refType(refType)
                .refId(refId)
                .sequence(0)
                .build();

        fileRepository.save(file);
        log.info("단일 이미지 저장 완료: refType={}, refId={}, imageIdentifier={}", refType, refId, imageIdentifier);
    }

    private void validateSequence(List<FileRequest> files) {
        // 파일 개수만큼 1부터 연속적인 sequence가 존재해야 함
        List<Integer> sequenceList = files.stream()
                .map(FileRequest::sequence)
                .sorted()
                .toList();

        // 기대하는 시퀀스 리스트 생성 (1부터 files.size()까지)
        List<Integer> expectedSequence = IntStream.rangeClosed(1, files.size())
                .boxed()
                .toList();

        if (!sequenceList.equals(expectedSequence)) {
            throw new FileException(FileErrorcode.INVALID_SEQUENCE);
        }
    }
}
