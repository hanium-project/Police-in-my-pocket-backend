package com.pocket.police.domain.userlocation.entity;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.global.common.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Getter  //클래스 내의 모든 필드의 getter 생성
@NoArgsConstructor   //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스임을 나타냄
@Table(name = "user_location")
public class UserLocation extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Account account;

    @Column(name = "start_location_title")
    private String startLocationTitle;

    @Column(name = "start_latitude")
    private Double startLatitude;

    @Column(name = "start_longitude")
    private Double startLongitude;

    @Column(name = "end_location_title")
    private String endLocationTitle;

    @Column(name = "end_latitude")
    private Double endLatitude;

    @Column(name = "end_longitude")
    private Double endLongitude;

    @Builder
    public UserLocation (Account account, String startLocationTitle, Double startLatitude, Double startLongitude,
                         String endLocationTitle, Double endLatitude, Double endLongitude)
    {
        this.account = account;
        this.startLocationTitle = startLocationTitle;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLocationTitle = endLocationTitle;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
    }

    public void update (String startLocationTitle, Double startLatitude, Double startLongitude,
                       String endLocationTitle, Double endLatitude, Double endLongitude)
    {
        this.startLocationTitle = startLocationTitle;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLocationTitle = endLocationTitle;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
    }
    public String getLocation (){
        String str = "출발지: " + startLocationTitle + ", 도착지: " + endLocationTitle;
        return str;
    }
}
