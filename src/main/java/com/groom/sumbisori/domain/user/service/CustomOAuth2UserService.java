package com.groom.sumbisori.domain.user.service;

import com.groom.sumbisori.domain.user.dto.common.CustomUserDetails;
import com.groom.sumbisori.domain.user.dto.common.KaKaoResponse;
import com.groom.sumbisori.domain.user.dto.common.OAuth2Response;
import com.groom.sumbisori.domain.user.entity.User;
import com.groom.sumbisori.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        if (registrationId == null) {
            throw new OAuth2AuthenticationException("유효하지않는 OAuth2 제공자입니다.");
        }
        OAuth2Response oAuth2Response = new KaKaoResponse(oAuth2User.getAttributes());
        User user = userService.createAndUpdateMember(oAuth2Response, registrationId);
        return new CustomUserDetails(user, oAuth2User.getAttributes());
    }
}
