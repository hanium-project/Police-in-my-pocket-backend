package com.pocket.police.domain.user.repository;

import com.pocket.police.domain.user.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    //Account findByUser_id(String user_id);
    Optional<Account> findByUserId(String id);   //repository에는 underbar를 사용하지 않는다.
}
