package com.testcode.yjp.last.domain;

import javax.persistence.*;

@Entity
public class Diet {
    @Id @GeneratedValue
    @Column(name = "DIET_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Member member;

    // 날짜

    @Column(name = "DIET_MENU", length = 30)
    private String menu;

    @Column(name = "DIET_MEMO")
    private String memo;

    @Column(name = "DIET_PHOTO")
    private String photo;

    @Column(name = "DIET_TIME", length = 15)
    private String time; // 아침, 점심, 저녁, 그외

    @Column(name = "DIET_CALORIE", length = 5)
    private Integer calorie;

    @Column(name = "DIET_IF")
    private String dietIf; // 0 = 식단기록, 1 = 목표

}
