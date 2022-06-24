package com.pocket.police.domain.user.controller;

import com.pocket.police.domain.user.dto.AccountRequestDto;
import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class TestController {
    private final AccountService accountService;
    @PostMapping(value = "/users/signup")
    @ResponseBody
    public Account save(@RequestBody AccountRequestDto requestDto) {
        Account account = accountService.save(requestDto);
        return account;
    }


}
