package com.pocket.police.domain.user_location.controller;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.domain.user.service.AccountService;
import com.pocket.police.domain.user_contact.dto.UserContactRequestDto;
import com.pocket.police.domain.user_location.dto.UserLocationRequestDto;
import com.pocket.police.domain.user_location.repository.UserLocationRepository;
import com.pocket.police.domain.user_location.service.UserLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/location")
public class UserLocationController {
    private final UserLocationRepository userLocationRepository;
    private final UserLocationService userLocationService;
    private final AccountRepository accountRepository;

    private final AccountService accountService;

    @GetMapping
    public String findContact(HttpServletRequest request, @RequestBody UserLocationRequestDto requestDto) {
        String user_id = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(user_id).get();
        return userLocationService.findLocation(account, requestDto);
    }

    @PostMapping
    public Long save(HttpServletRequest request,@RequestBody UserLocationRequestDto params) {
        String user_id = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(user_id).get();
        return userLocationService.save(account, params);
    }

    @PutMapping("/{location_id}")
    public Long update(HttpServletRequest request, @RequestBody UserLocationRequestDto params, @PathVariable("location_id") Long location_id) {
        String user_id = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(user_id).get();
        params.setAccount(account);
        return userLocationService.update(account, params, location_id
        );
    }

    @DeleteMapping("/{location_id}")
    public Long deleteContact(@PathVariable ("location_id") Long id) {
        userLocationRepository.deleteById(id); // 값 삭제
        return id;
    }

}
