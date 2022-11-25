package com.pocket.police.domain.userlocation.service;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.userlocation.dto.UserLocationRequestDto;
import com.pocket.police.domain.userlocation.entity.UserLocation;
import com.pocket.police.domain.userlocation.repository.UserLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // 꼭 필요한 요소(final) 자동 생성
@Service // 이 클래스는 서비스임을 알려줌
public class UserLocationService {

   // @Autowired    requiredArgsConstructor 이 있으면 사용하지 않아도 괜찮다.
    UserLocationRepository userLocationRepository;

    @Transactional
    public Long save(final Account account, final UserLocationRequestDto userLocationRequestDto) {
        userLocationRequestDto.setAccount(account);
        UserLocation entity = userLocationRepository.save(userLocationRequestDto.toEntity());
        return entity.getLocationId();
    }

    @Transactional
    public Long update(final Account account, final UserLocationRequestDto userLocationRequestDto, final Long locationId) {
        UserLocation entity = userLocationRepository.findById(locationId).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));;
        entity.update(userLocationRequestDto.getStartLocationTitle(), userLocationRequestDto.getStartLatitude(), userLocationRequestDto.getStartLongitude(), userLocationRequestDto.getEndLocationTitle(), userLocationRequestDto.getEndLatitude(), userLocationRequestDto.getEndLongitude());
        return entity.getLocationId();
    }

    @Transactional
    public String findLocation(final Account account,  final UserLocationRequestDto userLocationRequestDto) {
        UserLocation entity =  userLocationRepository.findByAccount(account);
        return entity.getLocation();
    }
}
