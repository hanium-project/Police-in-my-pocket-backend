package com.pocket.police.domain.danger_location.dto.response;

import lombok.Builder;

@Builder
public class DangerLocationResponseDto {
    public Double latitude;

    public Double longitude;

    public String locationTitle;

    public int occurrenceCount;
}
