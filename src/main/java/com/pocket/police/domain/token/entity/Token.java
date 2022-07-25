package com.pocket.police.domain.token.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@RedisHash(value = "token", timeToLive = 3000)
public class Token {
    @Id
    @Column(name = "token_key")
    private String key;

    @Column(name = "token_value")
    private String value;

    @Column(name = "token_create_at")
    private LocalDateTime createAt;

    @Builder
    public Token(String key, String value) {
        this.key = key;   //member's id
        this.value = value;  //token String
        this.createAt = LocalDateTime.now();
    }

    public void updateToken(String value) {
        this.value = value;
    }
}
