package com.pocket.police.domain.user_location.dto;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user_location.entity.UserLocation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserLocationRequestDto {
    public Account account;
    public String start_location_title;
    public Double start_latitude;
    public Double start_longitude;
    public String end_location_title;
    public Double end_latitude;
    public Double end_longitude;

    public UserLocation toEntity() {
        return UserLocation.builder()
                .account(account)
                .start_location_title(start_location_title)
                .start_latitude(start_latitude)
                .start_longitude(start_longitude)
                .end_location_title(end_location_title)
                .end_latitude(end_latitude)
                .end_longitude(end_longitude)
                .build();
    }
}
