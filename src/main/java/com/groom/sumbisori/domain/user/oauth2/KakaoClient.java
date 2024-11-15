package com.groom.sumbisori.domain.user.oauth2;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import com.groom.sumbisori.domain.user.dto.common.UnlinkResponse;
import com.groom.sumbisori.domain.user.error.UserErrorCode;
import com.groom.sumbisori.domain.user.error.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Component
@Slf4j
public class KakaoClient {
    private static final String KAKAO_UNLINK_URL = "https://kapi.kakao.com/v1/user/unlink";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_PREFIX = "KakaoAK ";
    private static final String CONTENT_TYPE = APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8";
    private static final String TARGET_ID_TYPE_KEY = "target_id_type";
    private static final String TARGET_ID_TYPE_VALUE = "user_id";
    private static final String TARGET_ID_KEY = "target_id";

    private final RestClient restClient;

    @Value("${custom.oauth2.kakao.admin-key}")
    private String adminKey;

    public void unlink(String providerId) {
        MultiValueMap<String, String> requestBody = createRequestBody(providerId);
        UnlinkResponse response = sendUnlinkRequest(requestBody);
        log.info("카카오 providerId {} 의 연결이 성공적으로 끊어졌습니다.", response.id());
    }

    private MultiValueMap<String, String> createRequestBody(String providerId) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add(TARGET_ID_TYPE_KEY, TARGET_ID_TYPE_VALUE);
        body.add(TARGET_ID_KEY, providerId);
        return body;
    }

    private UnlinkResponse sendUnlinkRequest(MultiValueMap<String, String> requestBody) {
        return restClient.post()
                .uri(KAKAO_UNLINK_URL)
                .header(AUTHORIZATION_HEADER, AUTHORIZATION_PREFIX + adminKey)
                .contentType(MediaType.valueOf(CONTENT_TYPE))
                .body(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    log.error("Kakao providerId {} 의 연결해제에 실패하였습니다. response status={}",
                            requestBody.getFirst(TARGET_ID_KEY), response.getStatusCode());
                    throw new UserException(UserErrorCode.KAKAO_UNLINK_FAILED);
                })
                .body(UnlinkResponse.class);
    }
}
