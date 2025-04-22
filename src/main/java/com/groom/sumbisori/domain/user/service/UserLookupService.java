package com.groom.sumbisori.domain.user.service;

import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.groom.sumbisori.domain.user.dto.response.UserNickname;
import com.groom.sumbisori.domain.user.dto.response.UserProfile;
import com.groom.sumbisori.domain.user.entity.User;
import com.groom.sumbisori.domain.user.error.UserErrorCode;
import com.groom.sumbisori.domain.user.error.exception.UserException;
import com.groom.sumbisori.domain.user.repository.UserQueryRepository;
import com.groom.sumbisori.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserLookupService {
    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;

    @Transactional(readOnly = true)
    public UserProfile getMyInfo(Long userId) {
        return userQueryRepository.findUserProfile(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public UserNickname getMyNickname(Long userId) {
        return UserNickname.of(userRepository.findNicknameById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND)));
    }
}
