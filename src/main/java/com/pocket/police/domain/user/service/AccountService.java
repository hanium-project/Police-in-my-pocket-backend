package com.pocket.police.domain.user.service;

import com.pocket.police.domain.user.dto.AccountMapper;
import com.pocket.police.domain.user.dto.AccountRequestDto;
import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import lombok.AllArgsConstructor;
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
