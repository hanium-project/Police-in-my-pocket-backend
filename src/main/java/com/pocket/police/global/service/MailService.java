package com.pocket.police.global.service;

import com.pocket.police.domain.user.dto.MailDto;
import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // 꼭 필요한 요소(final) 자동 생성
@Service // 이 클래스는 서비스임을 알려줌
public class MailService {
    private final AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "wisjang1@naver.com";


    public MailDto createMailAndChangePassword(String userId, String userName) {
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userId);
        dto.setTitle(userName + "님의 폴인포 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. 폴인포 임시비밀번호 안내 관련 이메일 입니다." + "[" + userName + "]" + "님의 임시 비밀번호는 "
                + str + " 입니다.");
        updatePassword(userId, str);
        return dto;
    }

    public void updatePassword(String userId, String password) {
        String pw = passwordEncoder.encode(password);
        Account account = accountRepository.findByUserId(userId).get();
        account.updatePassword(pw);
        accountRepository.save(account);
    }


    public String getTempPassword () {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend (MailDto mailDto){
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
}