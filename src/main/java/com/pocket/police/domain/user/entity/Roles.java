package com.pocket.police.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {
    USER("USER"),
    ADMIN("ADMIN");

    private String roles;
}
