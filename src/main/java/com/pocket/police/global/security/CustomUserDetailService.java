package com.pocket.police.global.security;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
       // Account account = accountRepository.findByUser_id(userId);
       // return accountRepository.findByUser_id(userId)
               // .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없음")) -> 이 부분이 안 먹힌다

       // if(account == null) new UsernameNotFoundException("해당하는 사용자를 찾을 수 없습니다.");
       // else return account;

        return accountRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("아이디 혹은 비밀번호가 틀렸습니다."));
    }
}
