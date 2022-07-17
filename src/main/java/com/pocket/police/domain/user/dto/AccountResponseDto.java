package com.pocket.police.domain.user.dto;

import com.pocket.police.domain.user.entity.Account;

public class AccountResponseDto {

    private String userId;
    private String password;
    private String name;
    private java.sql.Date birth;
    private String address;
    private String phoneNumber;
    private int userSirenCode;

    public AccountResponseDto(Account entity) {
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.birth = entity.getBirth();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.userSirenCode = entity.getUserSirenCode();
    }

}
