package com.testcode.yjp.last.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Setter
@Table(name = "Reply")
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cm_id")
    private Long id;

    @Column(name = "user_id")
    private String user_id;

    // 댓글 좋아요 수
    private int like_check=0;

    // 댓글 싫어요 수
    private int dislike_check=0;

    // board id
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
