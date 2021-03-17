package com.testcode.yjp.last.domain;

import javax.persistence.*;

@Entity
public class ExRecord {
    @Id @GeneratedValue
    @Column(name = "ER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Member member;

    // 날짜

    @Column(name = "ER_COUNT", length = 2)
    private Integer count;
    @Column(name = "ER_SET", length = 2)
    private Integer set;
    @Column(name = "ER_WEIGHT", length = 3)
    private Integer weight;
    @Column(name = "ER_TIME", length = 3)
    private Integer time;
    @Column(name = "ER_CALORIE", length = 5)
    private Integer calorie;

    @Column(name = "ER_FEEDBACK")
    private String feedback;

    @Column(name = "ER_IF")
    private String erIf; // 0 운동 기록, 1 목표
}
