package com.testcode.yjp.last.domain;

import javax.persistence.*;

@Entity
public class PtUser {
    @Id
    @GeneratedValue
    @Column(name = "PTUSER_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private Member member;

    //시작 날짜, pt신청 횟수, pt남은 횟수
}
