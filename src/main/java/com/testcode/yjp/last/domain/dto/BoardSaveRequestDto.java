package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private Member member;
    private String title;
    private String user_id;
    private String content;
    private int hit=0;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .user_id(user_id)
                .hit(hit)
                .build();
    }
}
