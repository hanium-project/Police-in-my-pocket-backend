package com.pocket.police.domain.user.controller;

import com.pocket.police.domain.user.dto.LoginTokenResponseDto;
import com.pocket.police.domain.user.dto.AccountRequestDto;
import com.pocket.police.domain.user.dto.MailDto;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.domain.user.dto.AccountRequestDto;
import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.domain.user.service.AccountService;
import com.pocket.police.global.config.MailService;
import com.pocket.police.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AccountController {
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    private String userPw;
    private final MailService mailService;

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


    @PostMapping("/users/signout")
    public String logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            //return token.substring(7);
            return accountService.logout(token.substring(7));
        }
        return "존재하지 않는 사용자입니다.";
    }

    //Email과 name의 일치여부를 check하는 컨트롤러
    @GetMapping("/users/findpw")
    public @ResponseBody Map<String, Boolean> pw_find(@RequestBody Map<String, String> user){
        Map<String,Boolean> json = new HashMap<>();
        boolean pwFindCheck = accountService.userEmailCheck(user.get("userId"), user.get("name"));

        System.out.println(pwFindCheck);
        json.put("check", pwFindCheck);
        return json;
    }

    //등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
    @PostMapping("/users/findpw/sendemail")
    public void sendEmail(@RequestBody Map<String, String> user){
        MailDto dto = mailService.createMailAndChangePassword(user.get("userId"), user.get("name"));
        mailService.mailSend(dto);

    }

}
