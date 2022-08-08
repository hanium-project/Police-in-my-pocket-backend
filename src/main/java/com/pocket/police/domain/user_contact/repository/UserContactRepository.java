package com.pocket.police.domain.user_contact.repository;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user_contact.entity.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long> {
        UserContact findByAccount (Account account);
}