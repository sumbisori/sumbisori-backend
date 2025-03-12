package com.groom.sumbisori.domain.experience.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.common.error.GlobalErrorCode;
import com.groom.sumbisori.common.springdoc.ApiExceptionExplanation;
import com.groom.sumbisori.common.springdoc.ApiResponseExplanations;
import com.groom.sumbisori.domain.experience.dto.request.ExperienceRequest;
import com.groom.sumbisori.domain.experience.dto.response.ExperienceResponse;
import com.groom.sumbisori.domain.experience.error.ExperienceErrorcode;
import com.groom.sumbisori.domain.file.error.FileErrorcode;
import com.groom.sumbisori.domain.place.error.PlaceErrorcode;
import com.groom.sumbisori.domain.seafood.error.SeafoodErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

@Tag(name = "experiences", description = "체험 API")
public interface ExperienceApi {
    @Operation(
            summary = "체험 등록",
            description = """
                        체험을 등록하는 API입니다.
                        - 체험 날짜: 필수
                        - 장소 ID: 필수
                        - 날씨 정보: 필수
                        - 동반자 유형: 필수
                        - 파일: 최대 10개 (imageIdentifier, 순서)
                        - 소감: 최대 150자
                        - 만족도: 1 ~ 5점
                        - 채집 정보: 최대 10개 (imageIdentifier, seafoodId, 수량)
                    """
    )
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(
                            value = GlobalErrorCode.class,
                            constant = GlobalErrorCode.Const.VALIDATION_FAILED,
                            name = "유효성 검사 실패",
                            description = """
                                    - 체험 날짜는 오늘 포함 이전 날짜만 가능합니다.
                                    - 장소 ID는 필수입니다.
                                    - 날씨 정보는 필수입니다.
                                    - 동반자 유형은 필수입니다.
                                    - 최대 10개의 파일을 요청할 수 있습니다.
                                    - 최대 150자까지 입력할 수 있습니다.
                                    - 만족도는 1점 이상이어야 합니다.
                                    - 만족도는 5점을 초과할 수 없습니다.
                                    - 파일의 imageIdentifier는 필수입니다.
                                    - 파일의 순서는 필수입니다. (1부터 순서대로 파일 사이즈만큼)
                                    - 파일의 순서는 1 이상 ~ 10까지 가능합니다.
                                    - seafoodId는 필수입니다.
                                    - quantity는 필수, 1 이상 ~ 20 이하여야 합니다.
                                    """
                    ),
                    @ApiExceptionExplanation(value = PlaceErrorcode.class, constant = PlaceErrorcode.Const.PLACE_NOT_FOUND, name = "체험 장소를 찾을 수 없습니다."),
                    @ApiExceptionExplanation(value = ExperienceErrorcode.class, constant = ExperienceErrorcode.Const.EXPERIENCE_DATE_INVALID, name = "체험 날짜는 오늘 포함 이전이어야 합니다."),
                    @ApiExceptionExplanation(value = FileErrorcode.class, constant = FileErrorcode.Const.INVALID_FILE, name = "유효하지 않은 imageIdentifier 입니다."),
                    @ApiExceptionExplanation(value = FileErrorcode.class, constant = FileErrorcode.Const.INVALID_SEQUENCE, name = "유효하지 않는 sequence 입니다.(1부터 시작 ~ 파일 사이즈까지)"),
                    @ApiExceptionExplanation(value = SeafoodErrorCode.class, constant = SeafoodErrorCode.Const.SEAFOOD_NOT_FOUND, name = "해산물을 찾을 수 없습니다."),
                    @ApiExceptionExplanation(value = FileErrorcode.class, constant = FileErrorcode.Const.S3_ERROR, name = "S3 서비스 오류입니다."),
            }
    )
    ResponseEntity<ExperienceResponse> createExperience(@LoginUser Long userId,
                                                        @RequestBody @Valid ExperienceRequest experienceRequest);

}
