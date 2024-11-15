package com.groom.sumbisori.domain.user.service;

import com.groom.sumbisori.domain.collection.repository.SeafoodCollectionQueryRepository;
import com.groom.sumbisori.domain.user.dto.response.UserProfile;
import com.groom.sumbisori.domain.user.entity.User;
import com.groom.sumbisori.domain.user.error.UserErrorCode;
import com.groom.sumbisori.domain.user.error.exception.UserException;
import com.groom.sumbisori.domain.user.oauth2.KakaoClient;
import com.groom.sumbisori.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserDeleteService userDeleteService;
    private final KakaoClient kakaoClient;
    private final UserRepository userRepository;
    private final SeafoodCollectionQueryRepository seafoodCollectionQueryRepository;

    public UserProfile getMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        int count = seafoodCollectionQueryRepository.sumQuantityByUserId(userId);
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
}
