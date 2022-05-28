package com.pocket.police.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter  //클래스 내의 모든 필드의 getter 생성
@NoArgsConstructor   //기본 생성자 자동 추가
@Entity    //테이블과 링크될 클래스임을 나타냄
@Table(name = "account")
public class Account {
    @Id   //해당 테이블의 PK 필드를 나타냄
//    @GeneratedValue(strategy = GenerationType.IDENTITY)   -> 자동으로 생성할 필요가 없기 때문에 사용하지 않음
    private String user_id;

    @Column(name = "password", nullable = false)  //해당 테이블의 칼럼을 나타냄
    private String password;

    @Column(name = "user_name", nullable = false)
    private String user_name;

    @Column(name = "birth", nullable = false)
    private java.sql.Date birth;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phone_number;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "user_siren_code", nullable = true)
    private int user_siren_code;

    @Builder  //해당 클래스의 빌더 패턴 클래스를 생성
    public Account(String user_id, String password, String user_name, java.sql.Date birth, String address, String phone_number, String gender, int user_siren_code) {
        this.user_id = user_id;
        this.password = password;
        this.user_name = user_name;
        this.birth = birth;
        this.address = address;
        this.phone_number = phone_number;
        this.gender = gender;
        this.user_siren_code = user_siren_code;
    }
}