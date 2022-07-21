package com.pocket.police.domain.safe_location.dto;

import com.pocket.police.domain.safe_location.entity.SafeLocation;
import com.pocket.police.domain.user.entity.Account;

public class LocationResponseDto {

    private Long location_id;

    private Double latitude;

    private Double longitude;

    private String locationTitle;

    public LocationResponseDto(SafeLocation entity) {
        this.location_id = entity.getLocation_id().longValue();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.locationTitle= entity.getLocationTitle();
    }
}
