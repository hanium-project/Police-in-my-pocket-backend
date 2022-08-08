package com.pocket.police.domain.user_contact.dto;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user_contact.entity.UserContact;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserContactRequestDto {
    public Account account;
    public String contact_1;
    public String contact_2;
    public String contact_3;

    public UserContact toEntity() {
        return UserContact.builder()
                .account(account)
                .contact_1(contact_1)
                .contact_2(contact_2)
                .contact_3(contact_3)
                .build();
    }
}
