package com.testcode.yjp.last.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Setter
@Table(name = "Reply")
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @Builder
    public Comment(String user_id, String comments, Long parentNum) {
        this.user_id = user_id;
        this.comments = comments;
        this.parentNum = parentNum;
    }


    public void update(String comments ){
        this.comments = comments;
    }
}
