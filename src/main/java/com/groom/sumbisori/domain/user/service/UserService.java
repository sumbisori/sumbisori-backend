package com.groom.sumbisori.domain.user.service;

import com.groom.sumbisori.domain.badge.dto.event.SignUpEvent;
import com.groom.sumbisori.domain.collection.repository.CollectionQueryRepository;
import com.groom.sumbisori.domain.user.dto.common.OAuth2Response;
import com.groom.sumbisori.domain.user.dto.response.UserProfile;
import com.groom.sumbisori.domain.user.entity.User;
import com.groom.sumbisori.domain.user.error.UserErrorCode;
import com.groom.sumbisori.domain.user.error.exception.UserException;
import com.groom.sumbisori.domain.user.oauth2.KakaoClient;
import com.groom.sumbisori.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserDeleteService userDeleteService;
    private final KakaoClient kakaoClient;
    private final UserRepository userRepository;
    private final CollectionQueryRepository collectionQueryRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public UserProfile getMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        int count = collectionQueryRepository.sumQuantityByUserId(userId);
        return UserProfile.builder()
                .count(count)
                .profileImageUrl(user.getProfileImageUrl())
                .nickname(user.getNickname())
                .build();
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        userDeleteService.delete(user);
        kakaoClient.unlink(user.getProviderId());
    }

    @Transactional
    public User createAndUpdateMember(OAuth2Response oAuth2Response, String providerType) {
        return userRepository.findByProviderIdAndProviderType(oAuth2Response.getProviderId(), providerType)
                .map(existingUser -> updateMember(existingUser, oAuth2Response))
                .orElseGet(() -> registerNewMember(oAuth2Response));
    }

    private User updateMember(User existingUser, OAuth2Response oAuth2Response) {
        existingUser.update(oAuth2Response.getEmail(), oAuth2Response.getNickname(),
                oAuth2Response.getProfileImage());
        return existingUser;
    }

    private User registerNewMember(OAuth2Response oAuth2Response) {
        try {
            User user = userRepository.save(oAuth2Response.toEntity());
            log.info("첫 소셜 로그인 및 회원가입 진행 - 제공자: {} / 사용자 ID: {}", oAuth2Response.getProvider(),
                    oAuth2Response.getProviderId());
            eventPublisher.publishEvent(SignUpEvent.of(user.getId()));
            return user;
        } catch (DataIntegrityViolationException e) {
            log.error("중복된 사용자 - 제공자: {}, providerId: {}", oAuth2Response.getProvider(),
                    oAuth2Response.getProviderId());
            throw new OAuth2AuthenticationException("duplicate user");
        }
    }
}
