package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public BoardUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }

    public BoardUpdateRequestDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
