package com.testcode.yjp.last.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Setter
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cm_id")
    private Long id;

    @Column(name = "user_id")
    private String user_id;

    private Long parentNum;

    @Column(name = "cm_comments", length = 2000)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "hb_num")
    private Board board;




    public void update(String comments ){
        this.comments = comments;
    }


}
