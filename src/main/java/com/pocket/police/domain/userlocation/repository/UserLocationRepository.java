package com.pocket.police.domain.userlocation.repository;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.userlocation.entity.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    UserLocation findByAccount (Account account);
}