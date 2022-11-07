package com.pocket.police.domain.danger_location.repository;

import com.pocket.police.domain.danger_location.entity.DangerLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DangerLocationRepository extends JpaRepository<DangerLocation, Long> {
    DangerLocation findByLatitudeAndLongitude(Double latitude, Double longitude);
    //해당 메소드를 정의한 후 service로 이용하지 않으면 오류가 난다.
    /*
        메소드 이름으로 쿼리를 생성할 수 있다
        위의 메소드를 통해 생성되는 JPQL은 SELECT m FROM Member m WHERE m.email = ?1 AND m.name = ?2
     */
}
