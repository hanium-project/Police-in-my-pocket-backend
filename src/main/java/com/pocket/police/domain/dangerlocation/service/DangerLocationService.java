package com.pocket.police.domain.dangerlocation.service;

import com.pocket.police.domain.dangerlocation.dto.mapper.DangerLocationMapper;
import com.pocket.police.domain.dangerlocation.dto.request.DangerLocationRequestDto;
import com.pocket.police.domain.dangerlocation.entity.DangerLocation;
import com.pocket.police.domain.dangerlocation.repository.DangerLocationRepository;
import lombok.AllArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Service
public class DangerLocationService {
    private final DangerLocationRepository dangerLocationRepository;
    private final DangerLocationMapper dangerLocationMapper;

    @Value("${spring.message.key}")
    private final String apiKey;

    @Value("${spring.message.secret}")
    private final String apiSecrete;

    @Transactional
    public DangerLocation create(DangerLocationRequestDto requestDto) {
        DangerLocation dangerLocation = dangerLocationRepository.findByLatitudeAndLongitude(requestDto.latitude, requestDto.longitude);

        if (dangerLocation == null) {
            return dangerLocationRepository.save(dangerLocationMapper.toEntity(requestDto));
        } else {
            dangerLocation.update(dangerLocation.getOccurenceCount());
            return dangerLocation;
        }
    }

    @Transactional
    public List<DangerLocation> findAll() {
        return dangerLocationRepository.findAll();
    }

    @Transactional
    public void sendEmergencyMessage(String name, String phoneNum) {
        Message msg = new Message(apiKey, apiSecrete);

        HashMap<String, String> msgParam = new HashMap<String, String>();
        msgParam.put("to", "");
        msgParam.put("from", phoneNum);
        msgParam.put("type", "SMS");
        msgParam.put("text", name + " - 한이음 신고 문자 테스트 : 긴급 신고 발생");

        try {
            JSONObject object = (JSONObject) msg.send(msgParam);
            System.out.println(object.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
