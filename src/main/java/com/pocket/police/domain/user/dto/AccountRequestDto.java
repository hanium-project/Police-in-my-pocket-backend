package com.pocket.police.domain.user.dto;

import com.pocket.police.domain.user.entity.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Collection;
import java.util.Collections;

@Setter // Lombok이 getter, setter 자동 생성
@Getter
@RequiredArgsConstructor // 꼭 필요한 요소(final) 자동 생성
//@NoArgsConstructor
public class AccountRequestDto {

    private String userId;
    private String password;
    private String name;
    private java.sql.Date birth;
    private String address;
    private String phoneNumber;
    private int userSirenCode;

    private String gender;

    //private String roles;

    public Account toEntity(){
        return Account.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .birth(birth)
                .address(address)
                .phoneNumber(phoneNumber)
                .userSirenCode(userSirenCode)
                .gender(gender)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
