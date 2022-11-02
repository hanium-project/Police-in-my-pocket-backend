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
    private Integer sirenCode;
    private String gender;

    public Account toEntity(){
        return Account.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .birth(birth)
                .address(address)
                .phoneNumber(phoneNumber)
                .sirenCode(sirenCode)
                .gender(gender)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {      // 이 부분은 보안 문제가 있기 때문에 수정이 조금 필요할 것 같다
        return password;
    }
}
