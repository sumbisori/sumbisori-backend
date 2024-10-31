package com.groom.demo.domain.token.service;

import com.groom.demo.common.util.JWTUtil;
import com.groom.demo.domain.token.entity.TokenType;
import com.groom.demo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {
    private final JWTUtil jwtUtil;

    public String createAccessToken(User user) {
        return jwtUtil.createToken(user, TokenType.ACCESS);
    }
}
