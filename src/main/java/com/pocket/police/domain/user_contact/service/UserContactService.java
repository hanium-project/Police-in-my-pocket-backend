package com.pocket.police.domain.user_contact.service;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.domain.user_contact.dto.UserContactRequestDto;
import com.pocket.police.domain.user_contact.entity.UserContact;
import com.pocket.police.domain.user_contact.repository.UserContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor // 꼭 필요한 요소(final) 자동 생성
@Service // 이 클래스는 서비스임을 알려줌
public class UserContactService {
    @Autowired
    private final UserContactRepository userContactRepository;

    @Transactional
    public Long save(final Account account, final UserContactRequestDto params) {
        params.setAccount(account);
        UserContact entity = userContactRepository.save(params.toEntity());
        return entity.getContactId();
    }


    @Transactional
    public Long update(final Account account, final UserContactRequestDto params, final Long contact_id) {
        UserContact entity = userContactRepository.findById(contact_id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));;
        entity.update(params.getContact_1(), params.getContact_2(), params.getContact_3());
        return entity.getContactId();
    }
    @Transactional
    public String findcontact(final Account account,  final UserContactRequestDto params){
        UserContact entity = userContactRepository.findByAccount(account);
        return entity.getContact();
    }

}
