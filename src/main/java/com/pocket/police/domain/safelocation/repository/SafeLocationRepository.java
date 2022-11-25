package com.pocket.police.domain.safelocation.repository;

import com.pocket.police.domain.safelocation.entity.SafeLocation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SafeLocationRepository extends JpaRepository<SafeLocation, Long> {
}
