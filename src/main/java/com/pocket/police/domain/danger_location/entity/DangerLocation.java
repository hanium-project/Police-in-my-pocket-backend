package com.pocket.police.domain.danger_location.entity;

import com.pocket.police.global.common.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name="danger_location")
@Entity
public class DangerLocation extends Timestamped {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long locationId;

    @Column(name="latitude", nullable = false)
    private Double latitude;

    @Column(name="longitude", nullable = false)
    private Double longitude;

    @Column(name="location_title", nullable = false)
    private String locationTitle;

    @Column(name="occurence_count", nullable = false)
    private Integer occurenceCount;

    @Builder
    public DangerLocation(Double latitude, Double longitude, String locationTitle, Integer occurenceCount) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationTitle = locationTitle;
        this.occurenceCount = 0;
    }

    public void update(Integer occurenceCount) {
        occurenceCount++;
        this.occurenceCount = occurenceCount;
    }
}
