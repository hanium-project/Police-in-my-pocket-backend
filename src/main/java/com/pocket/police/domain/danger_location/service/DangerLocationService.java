package com.pocket.police.domain.danger_location.service;

import com.pocket.police.domain.danger_location.controller.DangerLocationController;
import com.pocket.police.domain.danger_location.dto.mapper.DangerLocationMapper;
import com.pocket.police.domain.danger_location.dto.request.DangerLocationRequestDto;
import com.pocket.police.domain.danger_location.dto.response.DangerLocationResponseDto;
import com.pocket.police.domain.danger_location.entity.DangerLocation;
import com.pocket.police.domain.danger_location.repository.DangerLocationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class DangerLocationService {
    private final DangerLocationRepository dangerLocationRepository;
    private final DangerLocationMapper dangerLocationMapper;

    @Transactional
    public DangerLocation create(DangerLocationRequestDto requestDto) {
        DangerLocation dangerLocation = dangerLocationRepository.findByLatitudeAndLongitude(requestDto.latitude, requestDto.longitude);

        if(dangerLocation == null) {
            return dangerLocationRepository.save(dangerLocationMapper.toEntity(requestDto));
        } else {
            dangerLocation.update(dangerLocation.getOccurenceCount());
            return dangerLocation;
        }
    }

    @Transactional
    public List<DangerLocation> findAll() {
        return dangerLocationRepository.findAll();
    }
}
