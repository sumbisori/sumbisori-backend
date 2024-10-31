package com.groom.demo.domain.token.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    ACCESS(86400);

    private final int expirationTime;

}
