package com.pocket.police.domain.user_contact.entity;

import javax.persistence.*;

@Entity
@Table(name="user_emergency_contact")
public class UserEmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contact_id;

    private String user_id;

    private String contact;
}
