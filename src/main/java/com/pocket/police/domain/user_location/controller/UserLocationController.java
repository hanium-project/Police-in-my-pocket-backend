package com.pocket.police.domain.user_location.controller;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.domain.user_contact.dto.UserContactRequestDto;
import com.pocket.police.domain.user_location.dto.UserLocationRequestDto;
import com.pocket.police.domain.user_location.repository.UserLocationRepository;
import com.pocket.police.domain.user_location.service.UserLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/location")
public class UserLocationController {
    private final UserLocationRepository userLocationRepository;
    private final UserLocationService userLocationService;
    private final AccountRepository accountRepository;

    @GetMapping("/{user_id}")
    public String findContact(@PathVariable("user_id") String id, @RequestBody UserLocationRequestDto requestDto) {
        Account account = accountRepository.findById(id).get();
        return userLocationService.findLocation(account, requestDto);
    }

    @PostMapping("/{user_id}")
    public Long save(@PathVariable("user_id") String id,@RequestBody UserLocationRequestDto params) {
        Account account = accountRepository.findById(id).get();
        return userLocationService.save(account, params);
    }

    @PutMapping("/{user_id}/{location_id}")
    public Long update(@PathVariable ("user_id") String id, @RequestBody UserLocationRequestDto params, @PathVariable("location_id") Long location_id) {
        Account account = accountRepository.findById(id).get();
        params.setAccount(account);
        return userLocationService.update(account, params, location_id
        );
    }

    @DeleteMapping("/{user_id}/{location_id}")
    public Long deleteContact(@PathVariable ("location_id") Long id) {
        userLocationRepository.deleteById(id); // 값 삭제
        return id;
    }

}
