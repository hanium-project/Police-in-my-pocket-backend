package com.pocket.police.domain.user_contact.controller;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.domain.user_contact.dto.UserContactRequestDto;
import com.pocket.police.domain.user_contact.repository.UserContactRepository;
import com.pocket.police.domain.user_contact.service.UserContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/contact")
public class UserContactController {

    private final UserContactRepository userContactRepository;
    private final UserContactService userContactService;
    private final AccountRepository accountRepository;

<<<<<<< HEAD
    @GetMapping("/{user_id}")
    public String findContact(@PathVariable("user_id") String id, @RequestBody UserContactRequestDto requestDto) {
=======
    @GetMapping("/{user-id}")
    public String findContact(@PathVariable("user-id") String id, @RequestBody UserContactRequestDto requestDto) {
>>>>>>> e871377abdf8f74497ed0d1ea6fe1ecdfef6c307
        Account account = accountRepository.findById(id).get();
        return userContactService.findcontact(account, requestDto);
    }

<<<<<<< HEAD
    @PostMapping("/{user_id}")
    public Long save(@PathVariable("user_id") String id,@RequestBody UserContactRequestDto params) {
=======
    @PostMapping("/{user-id}")
    public Long save(@PathVariable("user-id") String id,@RequestBody UserContactRequestDto params) {
>>>>>>> e871377abdf8f74497ed0d1ea6fe1ecdfef6c307
        Account account = accountRepository.findById(id).get();
        return userContactService.save(account, params);
    }

<<<<<<< HEAD
    @PutMapping("/{user_id}/{contact_id}")
    public Long update(@PathVariable ("user_id") String id, @RequestBody UserContactRequestDto params, @PathVariable("contact_id") Long contact_id) {
=======
    @PutMapping("/{user-id}/{contact-id}")
    public Long update(@PathVariable ("user-id") String id, @RequestBody UserContactRequestDto params, @PathVariable("contact-id") Long contact_id) {
>>>>>>> e871377abdf8f74497ed0d1ea6fe1ecdfef6c307
        Account account = accountRepository.findById(id).get();
        params.setAccount(account);
        return userContactService.update(account, params, contact_id
        );
    }

<<<<<<< HEAD
    @DeleteMapping("/{user_id}/{contact_id}")
    public Long deleteContact(@PathVariable ("contact_id") Long id) {
=======
    @DeleteMapping("/{user-id}/{contact-id}")
    public Long deleteContact(@PathVariable ("contact-id") Long id) {
>>>>>>> e871377abdf8f74497ed0d1ea6fe1ecdfef6c307
        userContactRepository.deleteById(id); // 값 삭제
        return id;
    }
}

