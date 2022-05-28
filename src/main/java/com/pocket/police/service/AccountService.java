package com.pocket.police.service;

import com.pocket.police.Dto.AccountMapper;
import com.pocket.police.Dto.AccountRequestDto;
import com.pocket.police.entity.Account;
import com.pocket.police.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional
    public Account save(AccountRequestDto requestDto) {
        return accountRepository.save(accountMapper.toEntity(requestDto));
    }
}
