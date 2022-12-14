package com.pocket.police.domain.user.entity;

import com.pocket.police.global.common.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter  //클래스 내의 모든 필드의 getter 생성
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity    //테이블과 링크될 클래스임을 나타냄
@Table(name = "account")
public class Account extends Timestamped implements UserDetails {  //extends가 먼저 나온다
    // 현재 pk가 문자열로 되어있지만, DB 효율성을 위해 정수형으로 수정할 필요가 있다!
    @Id   //해당 테이블의 PK 필드를 나타냄
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)  //해당 테이블의 칼럼을 나타냄
    private String password;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "birth", nullable = false)
    private java.sql.Date birth;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "siren_code", nullable = true)
    private Integer sirenCode;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public void update(String userId, String password, String userName, java.sql.Date birth, String address, String phoneNumber, Integer sirenCode, String gender) {
        this.userId = userId;
        this.password = password;
        this.name = userName;
        this.birth = birth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sirenCode = sirenCode;
        this.gender = gender;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  //계정이 가진 권한 목록 리턴
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
