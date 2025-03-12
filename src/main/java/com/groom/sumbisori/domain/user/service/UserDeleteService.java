package com.groom.sumbisori.domain.user.service;

import com.groom.sumbisori.domain.user.entity.User;
import com.groom.sumbisori.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDeleteService {
    private final UserRepository userRepository;

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }
}
