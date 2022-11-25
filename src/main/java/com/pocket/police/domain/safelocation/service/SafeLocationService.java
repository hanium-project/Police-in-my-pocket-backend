package com.pocket.police.domain.safelocation.service;

import com.pocket.police.domain.safelocation.dto.LocationRequestDto;
import com.pocket.police.domain.safelocation.entity.SafeLocation;
import com.pocket.police.domain.safelocation.repository.SafeLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
