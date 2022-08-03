package com.pocket.police.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginTokenResponseDto {
    private String accessToken;
    private String refreshToken;
}
