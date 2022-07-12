package com.pocket.police.domain.user.dto;

import com.pocket.police.domain.user.entity.Account;

public class AccountResponseDto {

    private String user_id;
    private String password;
    private String user_name;
    private java.sql.Date birth;
    private String address;
    private String phone_number;
    private int user_siren_code;

    public AccountResponseDto(Account entity) {
        this.user_id = entity.getUser_id();
        this.password = entity.getPassword();
        this.user_name = entity.getUser_name();
        this.birth = entity.getBirth();
        this.address = entity.getAddress();
        this.phone_number = entity.getPhone_number();
        this.user_siren_code = entity.getUser_siren_code();
    }

}
