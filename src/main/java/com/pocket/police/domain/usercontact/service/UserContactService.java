package com.pocket.police.domain.usercontact.service;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.usercontact.dto.UserContactRequestDto;
import com.pocket.police.domain.usercontact.entity.UserContact;
import com.pocket.police.domain.usercontact.repository.UserContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@RequiredArgsConstructor // 꼭 필요한 요소(final) 자동 생성
@Service // 이 클래스는 서비스임을 알려줌
public class UserContactService {
    private final UserContactRepository userContactRepository;

    @Transactional
    public UserContact save(final Account account, final UserContactRequestDto userContactRequestDto) {
        userContactRequestDto.setAccount(account);
        UserContact userContactEntity = userContactRepository.save(userContactRequestDto.toEntity());
        return userContactEntity;
    }

    @Transactional
    public Long update(final Account account, final UserContactRequestDto userContactRequestDto, final Long contactId) {
        UserContact entity = userContactRepository.findById(contactId).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));;
        entity.update(userContactRequestDto.getName(), userContactRequestDto.getContact(), userContactRequestDto.getRelationship());
        return entity.getContactId();
    }

    @Transactional
    public String findContact(final Account account) {
        UserContact entity = userContactRepository.findByAccount(account);
        return entity.getContact();
    }
}
