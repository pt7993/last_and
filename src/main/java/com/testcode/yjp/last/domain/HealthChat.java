package com.testcode.yjp.last.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HealthChat extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "HC_ID")
    private Long id;

    @Column(name = "HC_TIME", length = 4)
    private Integer num;
    // 메세지 전송 시간
    @Column(name = "HC_CC")
    private String chatCont;
}
