package com.pocket.police.domain.safe_location.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name="safe_location")
@Entity


public class SafeLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long location_id;

    @Column(name="latitude", nullable = false)
    private Double latitude;

    @Column(name="longitude", nullable = false)
    private Double longitude;

    @Column(name="location_title", nullable = false)
    private String locationTitle;

    @Builder
    public SafeLocation(Long location_id, Double latitude, Double longitude, String locationTitle) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationTitle = locationTitle;
    }

    public void update(Long location_id, Double latitude, Double longitude, String locationTitle) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationTitle = locationTitle;
    }

}
