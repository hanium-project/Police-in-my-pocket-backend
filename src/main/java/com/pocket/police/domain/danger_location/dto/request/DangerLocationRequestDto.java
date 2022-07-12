package com.pocket.police.domain.danger_location.dto.request;

import lombok.Builder;

@Builder
public class DangerLocationRequestDto {
    public Double latitude;

    public Double longitude;

    public String locationTitle;
}
