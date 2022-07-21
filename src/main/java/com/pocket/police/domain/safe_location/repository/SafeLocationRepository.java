package com.pocket.police.domain.safe_location.repository;

import com.pocket.police.domain.safe_location.entity.SafeLocation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SafeLocationRepository extends JpaRepository<SafeLocation, Long>{
}
