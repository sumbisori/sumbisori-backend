package com.groom.demo.domain.seafood.dto;

import com.groom.demo.domain.seafood.Seafood;
import lombok.Data;

@Data
public class SeafoodRequest {
    private Seafood seafood;
    private int count;
}
