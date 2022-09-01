package com.pocket.police.domain.user_contact.controller;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.domain.user.service.AccountService;
import com.pocket.police.domain.user_contact.dto.UserContactRequestDto;
import com.pocket.police.domain.user_contact.repository.UserContactRepository;
import com.pocket.police.domain.user_contact.service.UserContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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
        String user_id = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(user_id).get();
        return userContactService.findcontact(account);
    }

    @PostMapping
    public Long save(HttpServletRequest request,@RequestBody UserContactRequestDto params) {
        String user_id = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(user_id).get();
        return userContactService.save(account, params);
    }

    @PutMapping("/{contact_id}")
    public Long update(HttpServletRequest request, @RequestBody UserContactRequestDto params, @PathVariable("contact_id") Long contact_id) {
        String user_id = accountService.tokenToUserId(request);
        Account account = accountRepository.findById(user_id).get();
        params.setAccount(account);
        return userContactService.update(account, params, contact_id);
    }

    @DeleteMapping("/{user_id}/{contact_id}")
    public Long deleteContact(@PathVariable ("contact_id") Long id) {
        userContactRepository.deleteById(id); // 값 삭제
        return id;
    }
}

