package com.pocket.police.domain.usercontact.controller;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.domain.user.service.AccountService;
import com.pocket.police.domain.usercontact.dto.UserContactRequestDto;
import com.pocket.police.domain.usercontact.entity.UserContact;
import com.pocket.police.domain.usercontact.repository.UserContactRepository;
import com.pocket.police.domain.usercontact.service.UserContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/contact")
public class UserContactController {

    private final UserContactRepository userContactRepository;
    private final UserContactService userContactService;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @GetMapping
    public String findContact(HttpServletRequest request) {
        String userId = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(userId).get();
        return userContactService.findContact(account);
    }

    @PostMapping
    public UserContact save(HttpServletRequest request, @RequestBody UserContactRequestDto contactRequestDto) {
        String userId = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(userId).get();
        return userContactService.save(account, contactRequestDto);
    }

    @PutMapping("/{contact_id}")
    public Long update(HttpServletRequest request, @RequestBody UserContactRequestDto contactRequestDto, @PathVariable("contact_id") Long contactId) {
        String userId = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(userId).get();
        contactRequestDto.setAccount(account);
        return userContactService.update(account, contactRequestDto, contactId);
    }

    @DeleteMapping("/{user_id}/{contact_id}")
    public Long deleteContact(HttpServletRequest request, @PathVariable("contact_id") Long id) {
        userContactRepository.deleteById(id); // ??? ??????
        return id;
    }
}

