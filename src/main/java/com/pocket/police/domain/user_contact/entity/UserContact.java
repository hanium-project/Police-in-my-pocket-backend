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

    @Column(name="contact_1")
    private String contact_1;

    @Column(name="contact_2")
    private String contact_2;

    @Column(name="contact_3")
    private String contact_3;

    @Builder
    public UserContact ( Account account, String contact_1, String contact_2, String contact_3) {
        this.account = account;
        this.contact_1 = contact_1;
        this.contact_2 = contact_2;
        this.contact_3 = contact_3;
    }

    public void update(String contact_1, String contact_2, String contact_3) {
        this.contact_1 = contact_1;
        this.contact_2 = contact_2;
        this.contact_3 = contact_3;
    }

    public String getContact(){
        String str = contact_1 + ", " + contact_2 + ", " + contact_3;
        return str;
    }


}
