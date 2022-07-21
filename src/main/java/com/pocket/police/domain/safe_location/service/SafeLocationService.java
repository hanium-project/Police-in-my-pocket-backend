package com.pocket.police.domain.safe_location.service;

import com.pocket.police.domain.safe_location.dto.LocationRequestDto;
import com.pocket.police.domain.safe_location.dto.LocationResponseDto;
import com.pocket.police.domain.safe_location.entity.SafeLocation;
import com.pocket.police.domain.safe_location.repository.SafeLocationRepository;
import com.pocket.police.domain.user.dto.AccountRequestDto;
import com.pocket.police.domain.user.dto.AccountResponseDto;
import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // 꼭 필요한 요소(final) 자동 생성
@Service // 이 클래스는 서비스임을 알려줌
public class SafeLocationService {

    private final SafeLocationRepository safelocationRepository;


    @Transactional
    public Long save(final LocationRequestDto params) {
        SafeLocation entity = safelocationRepository.save(params.toEntity());
        return entity.getLocation_id();
    }

    @Transactional
    public List<SafeLocation> findAll() {
        return safelocationRepository.findAll();
    }


}
