package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private int hit;
    private String author;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.hit = entity.getHit();
        this.modifiedDate = entity.getModDate();
    }
}
