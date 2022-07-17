package com.pocket.police.domain.user.entity;

import com.pocket.police.global.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter  //클래스 내의 모든 필드의 getter 생성
@NoArgsConstructor
@Entity    //테이블과 링크될 클래스임을 나타냄
@Table(name = "account")
public class Account extends Timestamped implements UserDetails {
    @Id   //해당 테이블의 PK 필드를 나타냄
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

    @Column(name = "user_siren_code", nullable = true)
    private int userSirenCode;

    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Builder  //해당 클래스의 빌더 패턴 클래스를 생성
    public Account(String userId, String password, String name, java.sql.Date birth, String gender, String address, String phoneNumber, int userSirenCode) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userSirenCode = userSirenCode;
        this.gender = gender;
    }

    public void update(String userId, String password, String userName, java.sql.Date birth, String address, String phoneNumber, int userSirenCode, String gender) {
        this.userId = userId;
        this.password = password;
        this.name = userName;
        this.birth = birth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userSirenCode = userSirenCode;
        this.gender = gender;
    }

//    public boolean login(String id,String pw){
//        if(user_id.equals(id) && password.equals(pw))
//            return true;
//        else
//            return false;
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
