package com.pocket.police.domain.danger_location.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name="danger_location")
@Entity
public class DangerLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long location_id;

    @Column(name="latitude", nullable = false)
    private Double latitude;

    @Column(name="longitude", nullable = false)
    private Double longitude;

    @Column(name="location_title", nullable = false)
    private String locationTitle;

    @Column(name="occurence_count", nullable = false)
    private int occurenceCount;

    @Builder
    public DangerLocation(Double latitude, Double longitude, String locationTitle, int occurenceCount) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationTitle = locationTitle;
        this.occurenceCount = 1;
    }

    public void update(int occurenceCount) {
        occurenceCount++;
        this.occurenceCount = occurenceCount;
    }
}
