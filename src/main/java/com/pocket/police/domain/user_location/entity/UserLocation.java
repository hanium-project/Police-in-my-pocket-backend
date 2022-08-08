package com.pocket.police.domain.user_location.entity;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.global.Timestamped;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter  //클래스 내의 모든 필드의 getter 생성
@NoArgsConstructor   //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스임을 나타냄
@Table(name = "user_location")
public class UserLocation extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long location_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Account account;

    @Column(name = "start_location_title")
    private String start_location_title;

    @Column(name = "start_latitude")
    private Double start_latitude;

    @Column(name = "start_longitude")
    private Double start_longitude;

    @Column(name = "end_location_title")
    private String end_location_title;

    @Column(name = "end_latitude")
    private Double end_latitude;

    @Column(name = "end_longitude")
    private Double end_longitude;

    @Builder
    public UserLocation (Account account, String start_location_title, Double start_latitude, Double start_longitude,
                         String end_location_title, Double end_latitude, Double end_longitude)
    {
        this.account = account;
        this.start_location_title = start_location_title;
        this.start_latitude = start_latitude;
        this.start_longitude = start_longitude;
        this.end_location_title = end_location_title;
        this.end_latitude = end_latitude;
        this.end_longitude = end_longitude;
    }

    public void update(String start_location_title, double start_latitude, double start_longitude,
                       String end_location_title, double end_latitude, double end_longitude)
    {
        this.start_location_title = start_location_title;
        this.start_latitude = start_latitude;
        this.start_longitude = start_longitude;
        this.end_location_title = end_location_title;
        this.end_latitude = end_latitude;
        this.end_longitude = end_longitude;
    }
    public String getLocation(){
        String str = "출발지: " + start_location_title + ", 도착지: " + end_location_title;
        return str;
    }
}
