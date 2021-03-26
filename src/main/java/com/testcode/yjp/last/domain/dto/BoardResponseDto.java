package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public BoardResponseDto(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
