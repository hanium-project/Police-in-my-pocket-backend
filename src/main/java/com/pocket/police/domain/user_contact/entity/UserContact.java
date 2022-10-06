package com.pocket.police.domain.user_contact.entity;

import com.pocket.police.domain.user.entity.Account;
import com.pocket.police.global.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter  //클래스 내의 모든 필드의 getter 생성
@NoArgsConstructor   //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스임을 나타냄
@Table(name = "user_emergency_contact")
public class UserContact extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private Account account;

    @Column(name="contact")
    private String contact;

    @Column(name="name")
    private String name;

    @Column(name="relationship")
    private String relationship;

    @Builder
    public UserContact ( Account account, String name, String contact, String relationship) {
        this.account = account;
        this.name = name;
        this.contact = contact;
        this.relationship = relationship;
    }

    public void update(String name, String contact, String relationship) {
        this.name = name;
        this.contact = contact;
        this.relationship = relationship;
    }
}
