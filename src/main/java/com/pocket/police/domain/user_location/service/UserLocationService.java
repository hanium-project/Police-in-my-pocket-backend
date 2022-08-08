package com.pocket.police.domain.user_location.service;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user_location.dto.UserLocationRequestDto;
import com.pocket.police.domain.user_location.entity.UserLocation;
import com.pocket.police.domain.user_location.repository.UserLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // 꼭 필요한 요소(final) 자동 생성
@Service // 이 클래스는 서비스임을 알려줌
public class UserLocationService {

    @Autowired
    UserLocationRepository userLocationRepository;

    @Transactional
    public Long save(final Account account, final UserLocationRequestDto params) {
        params.setAccount(account);
       UserLocation entity = userLocationRepository.save(params.toEntity());
        return entity.getLocation_id();
    }

    @Transactional
    public Long update(final Account account, final UserLocationRequestDto params, final Long location_id) {
        UserLocation entity = userLocationRepository.findById(location_id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));;
        entity.update(params.getStart_location_title(), params.getStart_latitude(), params.getStart_longitude(), params.getEnd_location_title(), params.getEnd_latitude(), params.getEnd_longitude());
        return entity.getLocation_id();
    }

    @Transactional
    public String findLocation(final Account account,  final UserLocationRequestDto params){
        UserLocation entity =  userLocationRepository.findByAccount(account);
        return entity.getLocation();
    }
}
