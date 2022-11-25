package com.pocket.police.domain.dangerlocation.dto.mapper;

import com.pocket.police.domain.dangerlocation.dto.request.DangerLocationRequestDto;
import com.pocket.police.domain.dangerlocation.dto.response.DangerLocationResponseDto;
import com.pocket.police.domain.dangerlocation.entity.DangerLocation;
import com.pocket.police.domain.dangerlocation.repository.DangerLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DangerLocationMapper {
    private final DangerLocationRepository dangerLocationRepository;

    public DangerLocation toEntity(DangerLocationRequestDto requestDto) {
        return DangerLocation.builder()
                .latitude(requestDto.latitude)
                .longitude(requestDto.longitude)
                .locationTitle(requestDto.locationTitle)
                .build();
    }

    public DangerLocationResponseDto fromEntity(DangerLocation dangerLocation) {
        return DangerLocationResponseDto.builder()
                .latitude(dangerLocation.getLatitude())
                .locationTitle(dangerLocation.getLocationTitle())
                .longitude(dangerLocation.getLongitude())
                .occurrenceCount(dangerLocation.getOccurenceCount())
                .build();
    }
}
