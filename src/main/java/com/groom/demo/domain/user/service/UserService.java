package com.groom.demo.domain.user.service;

import com.groom.demo.domain.collection.repository.SeafoodCollectionQueryRepository;
import com.groom.demo.domain.collection.repository.SeafoodCollectionRepository;
import com.groom.demo.domain.user.dto.UserProfile;
import com.groom.demo.domain.user.dto.request.LoginRequest;
import com.groom.demo.domain.user.dto.request.SignRequest;
import com.groom.demo.domain.user.dto.response.LoginResponse;
import com.groom.demo.domain.user.entity.User;
import com.groom.demo.domain.user.entity.User.UserRole;
import com.groom.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SeafoodCollectionQueryRepository seafoodCollectionQueryRepository;

    public UserProfile getMyInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        int count = seafoodCollectionQueryRepository.sumQuantityByUserId(userId);
        return UserProfile.builder()
                .count(count)
                .nickname(user.getNickname())
                .build();
    }

    public void signup(SignRequest signRequest) {
        if (userRepository.findByNickname(signRequest.getNickname()).isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
        }
        userRepository.save(User.builder()
                .nickname(signRequest.getNickname())
                .password(signRequest.getPassword())
                .userRole(UserRole.USER)
                .build());
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByNickname(loginRequest.getNickname())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return new LoginResponse(user.getId());
    }
}
