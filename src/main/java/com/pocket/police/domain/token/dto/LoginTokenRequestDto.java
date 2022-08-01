package com.pocket.police.domain.token.dto;

import lombok.Builder;

@Builder
public class LoginTokenRequestDto {
    public String key;
    public String value;
}
