package com.pocket.police.domain.user_contact.dto;


import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.domain.user_contact.entity.UserContact;
import lombok.Builder;

@Builder
public class UserContactResponseDto {
    public Account account;
    public String contact_1;
    public String contact_2;
    public String contacct_3;

    public UserContact fromEntity() {
        return UserContact.builder()
                .account(account)
                .contact_1(contact_1)
                .contact_2(contact_2)
                .contact_3(contacct_3)
                .build();
    }
}
