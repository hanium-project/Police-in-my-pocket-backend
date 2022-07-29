package com.pocket.police.domain.user.dto;

import com.pocket.police.domain.user.entity.Account;

public class AccountResponseDto {

    private String user_id;
    private String password;
    private String user_name;
    private java.sql.Date birth;
    private String address;
    private String gender;
    private String phone_number;
    private int user_siren_code;

    public AccountResponseDto(Account entity) {
        this.user_id = entity.getUserId();
        this.password = entity.getPassword();
        this.user_name = entity.getName();
        this.birth = entity.getBirth();
        this.address = entity.getAddress();
        this.gender = entity.getGender();
        this.phone_number = entity.getPhoneNumber();
        this.user_siren_code = entity.getUserSirenCode();
    }

}
