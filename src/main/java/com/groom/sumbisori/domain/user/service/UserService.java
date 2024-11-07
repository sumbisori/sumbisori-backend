package com.groom.sumbisori.domain.user.service;

import com.groom.sumbisori.domain.collection.repository.SeafoodCollectionQueryRepository;
import com.groom.sumbisori.domain.user.dto.UserProfile;
import com.groom.sumbisori.domain.user.entity.User;
import com.groom.sumbisori.domain.user.error.UserErrorCode;
import com.groom.sumbisori.domain.user.error.exception.UserException;
import com.groom.sumbisori.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
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

//    public void signup(SignRequest signRequest) {
//        if (userRepository.findByNickname(signRequest.getNickname()).isPresent()) {
//            throw new UserException(UserErrorCode.NICKNAME_DUPLICATION);
//        }
//        userRepository.save(User.builder()
//                .nickname(signRequest.getNickname())
//                .password(signRequest.getPassword())
//                .userRole(UserRole.USER)
//                .build());
//    }
//
//    public LoginResponse login(LoginRequest loginRequest) {
//        User user = userRepository.findByNickname(loginRequest.getNickname())
//                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
//
//        if (!user.getPassword().equals(loginRequest.getPassword())) {
//            throw new UserException(UserErrorCode.USER_NOT_MATCHED);
//        }
//        return new LoginResponse(user.getId());
//    }
}
