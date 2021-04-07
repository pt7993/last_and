package com.testcode.yjp.last.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board_likes")
public class Likes {

    // 중복 추천 체크
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "like_id")
    private Long id;

    // 아이디당 1번만 추천이 가능함
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 게시판글에 대해서 1번만 추천이 가능함
    @ManyToOne
    @JoinColumn(name = "hb_num")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "cm_id")
    private Comment comment;

    public Likes(Member member, Board board, Comment comment) {
        this.member = member;
        this.board = board;
        this.comment = comment;
    }

}
