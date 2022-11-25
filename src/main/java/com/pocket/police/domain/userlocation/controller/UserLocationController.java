package com.pocket.police.domain.userlocation.controller;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.domain.user.service.AccountService;
import com.pocket.police.domain.userlocation.dto.UserLocationRequestDto;
import com.pocket.police.domain.userlocation.repository.UserLocationRepository;
import com.pocket.police.domain.userlocation.service.UserLocationService;
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
        String userId = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(userId).get();
        return userLocationService.findLocation(account, requestDto);
    }

    @PostMapping
    public Long save(HttpServletRequest request,@RequestBody UserLocationRequestDto requestDto) {
        String userId = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(userId).get();
        return userLocationService.save(account, requestDto);
    }

    @PutMapping("/{location_id}")
    public Long update(HttpServletRequest request, @RequestBody UserLocationRequestDto requestDto, @PathVariable("location_id") Long locationId) {
        String userId = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(userId).get();
        requestDto.setAccount(account);
        return userLocationService.update(account, requestDto, locationId);
    }

    @DeleteMapping("/{location_id}")
    public Long deleteContact(@PathVariable("location_id") Long locationId) {
        userLocationRepository.deleteById(locationId); // 값 삭제
        return locationId;
    }

}
