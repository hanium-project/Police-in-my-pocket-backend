package com.pocket.police.domain.user_contact.dto;


import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user_contact.entity.UserContact;
import lombok.Builder;

@Builder
public class UserContactResponseDto {
    public Account account;
    public String contact;
    public String relationship;
    public String name;

    public UserContact fromEntity() {
        return UserContact.builder()
                .account(account)
                .name(name)
                .relationship(relationship)
                .contact(contact)
                .build();
    }
}
