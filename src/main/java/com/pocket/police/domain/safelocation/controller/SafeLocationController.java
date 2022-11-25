package com.pocket.police.domain.safelocation.controller;


import com.pocket.police.domain.safelocation.dto.LocationRequestDto;
import com.pocket.police.domain.safelocation.entity.SafeLocation;
import com.pocket.police.domain.safelocation.service.SafeLocationService;
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
