package com.pocket.police.domain.dangerlocation.dto.response;

import lombok.Builder;

@Builder
public class DangerLocationResponseDto {
    public Double latitude;

    public Double longitude;

    public String locationTitle;

    public Integer occurrenceCount;
}
