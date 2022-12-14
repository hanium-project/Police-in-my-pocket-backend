package com.pocket.police.domain.safelocation.dto;

import com.pocket.police.domain.safelocation.entity.SafeLocation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // 꼭 필요한 요소(final) 자동 생성

public class LocationRequestDto {

    private Long location_id;

    private Double latitude;

    private Double longitude;

    private String locationTitle;

    public SafeLocation toEntity(){
        return SafeLocation.builder()
                .location_id(location_id)
                .latitude(latitude)
                .longitude(longitude)
                .locationTitle(locationTitle)
                .build();
    }

}
