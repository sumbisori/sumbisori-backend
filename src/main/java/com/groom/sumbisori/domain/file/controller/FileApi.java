package com.groom.sumbisori.domain.file.controller;

import com.groom.sumbisori.common.error.GlobalErrorCode;
import com.groom.sumbisori.common.springdoc.ApiExceptionExplanation;
import com.groom.sumbisori.common.springdoc.ApiResponseExplanations;
import com.groom.sumbisori.domain.file.dto.PreSignedUrlRequest;
import com.groom.sumbisori.domain.file.dto.PreSignedUrlResponse;
import com.groom.sumbisori.domain.file.dto.SeafoodRecognitionResponse;
import com.groom.sumbisori.domain.file.error.FileErrorcode;
import com.groom.sumbisori.domain.file.error.FileErrorcode.Const;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "files", description = "파일 API")
public interface FileApi {
    @Operation(
            summary = "S3 Pre-signed URL 생성",
            description = """
                        S3 Pre-signed URL을 생성하는 API입니다. (유효 시간: 3분)
                    
                        - URL 요청 개수: 1 ~ 10개
                        - 파일 크기: 1 ~ 10 * 1024 * 1024 bytes (1MB ~ 10MB)
                        - 지원되는 콘텐츠 타입: 
                            - image/jpeg
                            - image/png
                            - image/gif
                    """
    )
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(value = GlobalErrorCode.class, constant = GlobalErrorCode.Const.VALIDATION_FAILED, name = "유효성 검사 실패(개수 1~10개, file size 1 ~ 10 * 1024 * 1024 byte)"),
                    @ApiExceptionExplanation(value = FileErrorcode.class, constant = Const.INVALID_IMAGE_CONTENT_TYPE, name = "허용되지 않은 이미지 파일 형식입니다. (image/jpeg, image/png, image/gif)"),
            }
    )
    ResponseEntity<List<PreSignedUrlResponse>> createPreSignedUrl(@Valid @RequestBody PreSignedUrlRequest request);

    @Operation(
            summary = "이미지 분석",
            description = "S3에 업로드된 이미지를 분석하여 결과를 반환하는 API입니다."
    )
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(value = FileErrorcode.class, constant = Const.INVALID_FILE, name = "유효하지 않은 objectKey 입니다."),
                    @ApiExceptionExplanation(value = FileErrorcode.class, constant = Const.S3_ERROR, name = "S3 서비스 오류입니다."),
                    @ApiExceptionExplanation(value = FileErrorcode.class, constant = Const.CHAT_GPT_ERROR, name = "Chat GPT 서비스 오류입니다.")
            }
    )
    ResponseEntity<List<SeafoodRecognitionResponse>> imageAnalyze(String key);
}
