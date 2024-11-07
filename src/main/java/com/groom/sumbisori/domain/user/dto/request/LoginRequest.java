package com.groom.sumbisori.domain.user.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String nickname;
    private String password;
}
