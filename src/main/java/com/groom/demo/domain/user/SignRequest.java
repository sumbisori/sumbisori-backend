package com.groom.demo.domain.user;

import lombok.Data;

@Data
public class SignRequest {
    private String nickname;
    private String password;
}
