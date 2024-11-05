package com.groom.demo.domain.user.dto.request;

import lombok.Data;

@Data
public class SignRequest {
    private String nickname;
    private String password;
}
