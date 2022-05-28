package com.pocket.police.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor   //모든 필드값을 파라미터로 받는 생성자를 만듦
//DTO는 생성자가 필요한데 해당 롬복을 설정하면 생성자를 만들지 않아도 됨
public class AccountRequestDto {
    private String user_id;

    private String password;

    private String user_name;

    private java.sql.Date birth;

    private String phone_number;

    private String gender;

    private String address;

    private int user_siren_code;
}
