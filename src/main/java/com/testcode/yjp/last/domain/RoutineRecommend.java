package com.testcode.yjp.last.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RoutineRecommend {
    @Id
    @GeneratedValue
    @Column(name = "RR_ID")
    private Long id;

    @Column(name = "RR_NAME", length = 30)
    private String name;
    @Column(name = "RR_EX_CODE", length = 5)
    private String exCode;
    @Column(name = "RR_SET", length = 2)
    private Integer set;
}
