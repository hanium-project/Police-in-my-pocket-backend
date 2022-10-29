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
    public String startLocationTitle;
    public Double startLatitude;
    public Double startLongitude;
    public String endLocationTitle;
    public Double endLatitude;
    public Double endLongitude;

    public UserLocation toEntity () {
        return UserLocation.builder()
                .account(account)
                .startLocationTitle(startLocationTitle)
                .startLatitude(startLatitude)
                .startLongitude(startLongitude)
                .endLocationTitle(endLocationTitle)
                .endLatitude(endLatitude)
                .endLongitude(endLongitude)
                .build();
    }
}
