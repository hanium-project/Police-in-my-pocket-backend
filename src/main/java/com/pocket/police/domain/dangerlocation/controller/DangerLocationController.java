package com.pocket.police.domain.dangerlocation.controller;

import com.pocket.police.domain.dangerlocation.dto.mapper.DangerLocationMapper;
import com.pocket.police.domain.dangerlocation.dto.request.DangerLocationRequestDto;
import com.pocket.police.domain.dangerlocation.dto.response.DangerLocationResponseDto;
import com.pocket.police.domain.dangerlocation.entity.DangerLocation;
import com.pocket.police.domain.dangerlocation.service.DangerLocationService;
import com.pocket.police.domain.user.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/emergency")
public class DangerLocationController {
    private final DangerLocationService dangerLocationService;
    private final DangerLocationMapper dangerLocationMapper;
    private final AccountService accountService;

    @PostMapping
    public DangerLocationResponseDto create(@RequestBody DangerLocationRequestDto requestDto) {
        DangerLocation dangerLocation = dangerLocationService.create(requestDto);
        return dangerLocationMapper.fromEntity(dangerLocation);
    }

    @GetMapping
    public List<DangerLocation> findAll() {
        return dangerLocationService.findAll();
    }

    @PostMapping("/{contact}")
    public String sendEmergencySMS(HttpServletRequest request, @PathVariable String contact) {
        String userId = accountService.tokenToUserId(request);
        dangerLocationService.sendEmergencyMessage(userId, contact);
        return userId;
    }
}
