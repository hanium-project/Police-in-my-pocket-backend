package com.pocket.police.domain.user.repository;

import com.pocket.police.domain.user.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByUserId(String id);   //repository에는 underbar를 사용하지 않는다.
}
