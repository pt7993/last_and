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
    private String content;
    private int hit=0;
    private String author;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .hit(hit)
                .build();
    }
}
