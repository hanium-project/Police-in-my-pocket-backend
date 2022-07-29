package com.pocket.police.global.security;


import com.pocket.police.global.config.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String secretKey = "policeInMyPocket";
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    //토큰 유효시간은 정하지 않음. 향후 수정 예정
    private Long tokenValidTime = 1000L * 60 * 3;   //3m
    private Long refreshTokenValid = 1000L * 60 * 60 * 24;  //1d
    private final UserDetailsService userDetailsService;
    private final RedisService redisService;

    //객체 초기화, secretKey(디코딩용) Base64로 인코딩
    @PostConstruct  //의존성 주입이 이루어진 후 초기화 수행
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT token 생성
    public String createToken(String userId, List<String> roles, Long validTime) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("Roles", roles);   //역할
        Date date = new Date();

        return Jwts.builder()
                .setSubject(userId)
                .claim(AUTHORITIES_KEY, roles)
//                .setClaims(claims)  //payload: 토큰에 담아 전송하는 데이터가 포함된다. 이러한 각 정보를 클레임(claim)이라 하며, 키-값으로 구성됨
                .setIssuedAt(date)   //토큰 발행 시간
                .setExpiration(new Date(date.getTime() + validTime))  //향후 수정 예정
                .signWith(SignatureAlgorithm.HS256, secretKey)   // 암호화 알고리즘과 서명에 들어갈 시크릿 키
//                //서명을 위해서는 인코딩된 헤더와 페이로드, 시크릿 키, 알고리즘이 필요하다.
                .compact();

    }

    //access token 생성
    public String createAccessToken(String userId, List<String> roles) {
        return this.createToken(userId, roles, tokenValidTime);
    }

    //refresh token 생성
    public String createRefreshToken(String userId, List<String> roles) {
        String refreshToken = this.createToken(userId, roles, refreshTokenValid);
        redisService.setValues(userId, refreshToken, Duration.ofMillis(tokenValidTime));
        return refreshToken;
    }

    //JWT 토큰 인증 정보 조회(토큰 복호화)
    public Authentication getAuthentication(String token) {
        //parseClaims : 만료된 토큰이어도 정보를 꺼내기 위해 사용
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰에서 회원 정보 추출
    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    //토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    //header에서 token값을 가져온다
    //header: JWT를 검증하는 암호화 알고리즘, 토큰 타입 등이 포함된다
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
       // return request.getHeader("X-AUTH-TOKEN");    //X-AUTH-TOKEN이란?
    }
}
