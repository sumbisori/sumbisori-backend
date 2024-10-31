package com.groom.demo.domain.user;

import com.groom.demo.domain.user.entity.User;
import com.groom.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserProfile getMyInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return UserProfile.builder()
                .userId(userId)
                .nickname(user.getNickname())
                .build();
    }

    public void signup(SignRequest signRequest) {
        //중복검사
        if (userRepository.findByNickname(signRequest.getNickname()).isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
        }
        userRepository.save(User.builder()
                .nickname(signRequest.getNickname())
                .password(signRequest.getPassword())
                .build());
    }

    public Long login(SignRequest signRequest) {
        User user = userRepository.findByNickname(signRequest.getNickname())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (!user.getPassword().equals(signRequest.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return user.getId();
    }
}
