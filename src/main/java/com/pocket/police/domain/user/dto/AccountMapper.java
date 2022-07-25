package com.pocket.police.domain.user.dto;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final AccountRepository accountRepository;

    public Account toEntity(AccountRequestDto requestDto) {
        //저장하는 클래스 toEntity(entity로 데이터를 전송)
        return Account.builder()
                .userId(requestDto.getUserId())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .birth(requestDto.getBirth())
                .address(requestDto.getAddress())
                .gender(requestDto.getGender())
                .phoneNumber(requestDto.getPhoneNumber())
                .userSirenCode(requestDto.getUserSirenCode())
                .build();
    }
}
