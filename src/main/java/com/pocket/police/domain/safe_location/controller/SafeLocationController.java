package com.pocket.police.domain.safe_location.controller;


import com.pocket.police.domain.safe_location.dto.LocationRequestDto;
import com.pocket.police.domain.safe_location.dto.LocationResponseDto;
import com.pocket.police.domain.safe_location.entity.SafeLocation;
import com.pocket.police.domain.safe_location.repository.SafeLocationRepository;
import com.pocket.police.domain.safe_location.service.SafeLocationService;
import com.pocket.police.domain.user.dto.AccountRequestDto;
import com.pocket.police.domain.user.repository.AccountRepository;
import com.pocket.police.domain.user.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SafeLocationController {

    private final SafeLocationService safelocationservice;

    @PostMapping("/v1/safe")
    public Long save(@RequestBody final LocationRequestDto params) {
        return safelocationservice.save(params);
    }

    @GetMapping("/v1/safe")
    public List<SafeLocation> findAll() {
        return safelocationservice.findAll();
    }

}
