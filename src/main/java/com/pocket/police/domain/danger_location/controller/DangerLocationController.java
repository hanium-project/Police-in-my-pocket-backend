package com.pocket.police.domain.danger_location.controller;

import com.pocket.police.domain.danger_location.dto.mapper.DangerLocationMapper;
import com.pocket.police.domain.danger_location.dto.request.DangerLocationRequestDto;
import com.pocket.police.domain.danger_location.dto.response.DangerLocationResponseDto;
import com.pocket.police.domain.danger_location.entity.DangerLocation;
import com.pocket.police.domain.danger_location.service.DangerLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/emergency")
public class DangerLocationController {
    private final DangerLocationService dangerLocationService;
    private final DangerLocationMapper dangerLocationMapper;

    @PostMapping
    public DangerLocationResponseDto create(@RequestBody DangerLocationRequestDto requestDto) {
        DangerLocation dangerLocation = dangerLocationService.create(requestDto);
        return dangerLocationMapper.fromEntity(dangerLocation);
    }

    @GetMapping
    public List<DangerLocation> findAll() {
        return dangerLocationService.findAll();
    }

}
