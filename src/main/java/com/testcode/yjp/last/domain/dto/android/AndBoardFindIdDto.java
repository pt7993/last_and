package com.testcode.yjp.last.domain.dto.android;

import com.testcode.yjp.last.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class AndBoardFindIdDto {
    private Long id;
    private String title;
    private String user_id;
    private String content;
    private Member member;

    private String regDate;
    private String modDate;

    private String bool;
    private int recommend_cnt = 0;

    public AndBoardFindIdDto(Long id, String title, String user_id, String content, Member member, String regDate, String modDate) {
        this.id = id;
        this.title = title;
        this.user_id = user_id;
        this.content = content;
        this.member = member;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
