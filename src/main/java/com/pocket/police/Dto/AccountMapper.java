package com.pocket.police.Dto;

import com.pocket.police.entity.Account;
import com.pocket.police.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final AccountRepository accountRepository;

    public Account toEntity(AccountRequestDto requestDto) {
        //저장하는 클래스 toEntity(entity로 데이터를 전송)
        return Account.builder()
                .user_id(requestDto.getUser_id())
                .password(requestDto.getPassword())
                .user_name(requestDto.getUser_name())
                .birth(requestDto.getBirth())
                .address(requestDto.getAddress())
                .gender(requestDto.getGender())
                .phone_number(requestDto.getPhone_number())
                .user_siren_code(requestDto.getUser_siren_code())
                .build();
    }
}
