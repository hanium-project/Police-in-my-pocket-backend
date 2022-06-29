package com.pocket.police.domain.user.repository;

import com.pocket.police.domain.user.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
