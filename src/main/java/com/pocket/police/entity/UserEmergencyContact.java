package com.pocket.police.entity;

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
