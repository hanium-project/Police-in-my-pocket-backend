package com.pocket.police.domain.usercontact.dto;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.usercontact.entity.UserContact;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserContactRequestDto {
    public Account account;
    public String name;
    public String contact;
    public String relationship;

    public UserContact toEntity() {
        return UserContact.builder()
                .account(account)
                .name(name)
                .contact(contact)
                .relationship(relationship)
                .build();
    }
}
