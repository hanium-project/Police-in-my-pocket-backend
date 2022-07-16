package com.pocket.police.global.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String secretKey = "policeInMyPocket";

    //토큰 유효시간은 정하지 않음

    private final UserDetailsService userDetailsService;

    //객체 초기화, secretKey(디코딩용) Base64로 인코딩
    @PostConstruct  //의존성 주입이 이루어진 후 초기화 수행
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT token 생성
    public String CreateToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);
        Date date = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)   //토큰 발행 시간
               // .setExpiration(new Date(date.getTime() + 유효시간 무한대 설정))
                .compact();
    }
}
