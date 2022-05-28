package com.pocket.police.controller;

import com.pocket.police.Dto.AccountRequestDto;
import com.pocket.police.Dto.AccountResponseDto;
import com.pocket.police.entity.Account;
import com.pocket.police.service.AccountService;
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
