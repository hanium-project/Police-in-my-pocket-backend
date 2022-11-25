package com.pocket.police.domain.usercontact.repository;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.usercontact.entity.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long> {
        UserContact findByAccount(Account account);
}