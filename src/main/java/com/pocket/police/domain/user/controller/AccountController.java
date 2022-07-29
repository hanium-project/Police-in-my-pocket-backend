package com.pocket.police.domain.user.controller;

import com.pocket.police.domain.user.dto.LoginTokenResponseDto;
import com.pocket.police.domain.user.dto.AccountRequestDto;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.domain.user.service.AccountService;
import com.pocket.police.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private String userPw;

    @PostMapping("/users/signup")
    public String save(@RequestBody final AccountRequestDto params) {
        return accountService.save(params);
    }

    @PutMapping("/users/{userid}")
    public String updateAccount(@PathVariable ("userid") String id, @RequestBody AccountRequestDto requestDto) {
        return accountService.update(id, requestDto);
    }

    @DeleteMapping("/users/{userid}")
    public String deleteAccount(@PathVariable ("userid") String id) {
        accountRepository.deleteById(id); // 값 삭제
        return id;
    }


//    @PostMapping("/users/signin")
//    public String login(@RequestBody Map<String, String> user) {
//        Account account = accountRepository.findByUserId(user.get("userId"))
//                .orElseThrow(() -> new IllegalArgumentException("없는 사용자 id : " + user.get("userId")));
//
//        if(!passwordEncoder.matches(user.get("password"), account.getPassword())) {
//            throw new IllegalArgumentException("잘못된 비밀번호 입니다. " + user.get("password") + " / " + account.getPassword());
//        }
//
//        return "사용자 권한 : " + account.getRoles() + " " + jwtTokenProvider.CreateToken(account.getUserId(), account.getRoles());
//    }

    @PostMapping("/users/signin")
    public ResponseEntity<LoginTokenResponseDto> login(@RequestBody Map<String, String> user) {
        userPw = user.get("password");

        LoginTokenResponseDto responseDto = accountService.login(user.get("userId"), user.get("password"));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

        //return "사용자 권한 : " + account.getRoles() + " " + jwtTokenProvider.CreateToken(account.getUserId(), account.getRoles());
    }

    @GetMapping("/users/rsignin")
    public ResponseEntity<LoginTokenResponseDto> reLogin(@RequestParam("userId") String userId, @RequestParam("refreshToken") String refreshToken) {
        LoginTokenResponseDto responseDto = accountService.reIssueToken(userId, userPw, refreshToken);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
