package com.pocket.police.domain.safe_location.controller;


import com.pocket.police.domain.safe_location.dto.LocationResponseDto;
import com.pocket.police.domain.safe_location.entity.SafeLocation;
import com.pocket.police.domain.safe_location.service.SafeLocationService;
import com.pocket.police.domain.user.dto.AccountRequestDto;
import com.pocket.police.domain.user.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SafeLocationController {

    private final SafeLocationService safelocationservice;

    @GetMapping("/v1/safe")
    public List<SafeLocation> findAll() {
        return safelocationservice.findAll();
    }

}
