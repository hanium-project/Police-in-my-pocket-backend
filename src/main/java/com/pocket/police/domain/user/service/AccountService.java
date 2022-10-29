package com.pocket.police.domain.user.service;

import com.pocket.police.domain.user.dto.LoginTokenResponseDto;
import com.pocket.police.domain.user.dto.AccountRequestDto;
import com.pocket.police.domain.user.dto.AccountResponseDto;
import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.global.service.RedisService;
import com.pocket.police.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // 꼭 필요한 요소(final) 자동 생성
@Service // 이 클래스는 서비스임을 알려줌
public class AccountService {
    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;
    // @Autowired
    final PasswordEncoder passwordEncoder;    // 의존성 주입이 안되면 nullException 생김

    @Transactional
    public String save (final AccountRequestDto accountRequestDto) {
        // System.out.println(accountRequestDto.getPassword());
        String password = passwordEncoder.encode(accountRequestDto.getPassword());
        System.out.println(password);
        accountRequestDto.setPassword(password);
        Account entity = accountRepository.save(accountRequestDto.toEntity());
        return entity.getUserId();
    }

    @Transactional
    public List<AccountResponseDto> findAll () {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<Account> list = accountRepository.findAll(sort);
        return list.stream().map(AccountResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public String update (final String userId, final AccountRequestDto accountRequestDto) {

        Account entity = accountRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        entity.update(accountRequestDto.getUserId(), accountRequestDto.getPassword(), accountRequestDto.getName(), accountRequestDto.getBirth(), accountRequestDto.getAddress(), accountRequestDto.getPhoneNumber(),
                accountRequestDto.getSirenCode(), accountRequestDto.getGender());
        return userId;
    }


    @Transactional
    public LoginTokenResponseDto login (String userId, String password) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 사용자 id : " + userId));

        if(!passwordEncoder.matches(password, account.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        String accessToken = jwtTokenProvider.createAccessToken(account.getUserId(), account.getRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken(account.getUserId(), account.getRoles());

        return new LoginTokenResponseDto(accessToken, refreshToken);
    }

    @Transactional
    public LoginTokenResponseDto reIssueToken (String userId, String password, String refreshToken) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 사용자 id : " + userId));

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        jwtTokenProvider.checkRefreshToken(userId, refreshToken);
        String accessToken = jwtTokenProvider.createAccessToken(account.getUserId(), account.getRoles());

        return new LoginTokenResponseDto(accessToken, refreshToken);
    }

    public String logout (String accessToken) {
        // AccessToken 검증하기 (유효성 검증)
        if(!jwtTokenProvider.validateToken (accessToken)) {
            return "이미 만료된 토큰입니다.";
        }

        // AccessToken으로부터 user email을 가져옴
        Authentication authentication = jwtTokenProvider.getAuthentication (accessToken);

        // Redis에서 해당 email로 저장된 refreshToken이 있는지 확인후 있으면 삭제
        // 여기서 주의해야할 건 refresh token을 삭제하는 것이지 access token이 아님을 기억할것
        if(redisService.getValues (authentication.getName()) != null) {
            redisService.deleteValues (authentication.getName());
        }

        // 해당 토큰의 유효시간을 가져와 블랙리스트로 저장
        Long expiration = jwtTokenProvider.getExpiration (accessToken);
        redisService.setValues(authentication.getName () + " is logout", accessToken, Duration.ofMillis(expiration));

        return "로그아웃 되었습니다.";
    }

    public boolean userEmailCheck (String userId, String userName) {

        Account account = accountRepository.findByUserId (userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 사용자 id : " + userId));
        if(account != null && account.getName().equals(userName)) {
            return true;
        }
        else {
            return false;
        }
    }

    public String tokenToUserId (HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String userToken = null;
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            userToken = bearerToken.substring(7);
        }

        String userId = jwtTokenProvider.getUserId(userToken);

        return userId;
    }
}
