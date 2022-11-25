package com.pocket.police.domain.user.dto;

import com.pocket.police.domain.user.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class AccountResponseDto {
    private String userId;
    private String password;
    private String name;
    private java.sql.Date birth;
    private String address;
    private String phoneNumber;
    private Integer sirenCode;
    private String gender;

    public AccountResponseDto(Account entity) {
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.birth = entity.getBirth();
        this.address = entity.getAddress();
        this.gender = entity.getGender();
        this.phoneNumber = entity.getPhoneNumber();
        this.sirenCode = entity.getSirenCode();
    }

}
