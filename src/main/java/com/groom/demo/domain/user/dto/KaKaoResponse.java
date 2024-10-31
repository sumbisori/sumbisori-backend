package com.groom.demo.domain.user.dto;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KaKaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public KaKaoResponse(Map<String, Object> attribute) {
        log.info("attribute = {}", attribute);
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        return ((Map<String, Object>) attribute.get("kakao_account")).get("email").toString();
    }

    @Override
    public String getNickname() {
        Map<String, Object> properties = (Map<String, Object>) attribute.get("properties");
        return properties != null ? properties.get("nickname").toString() : null;
    }

    @Override
    public String getProfileImage() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
        Map<String, Object> profile = kakaoAccount != null ? (Map<String, Object>) kakaoAccount.get("profile") : null;
        return profile != null ? profile.get("profile_image_url").toString() : null;
    }
}
