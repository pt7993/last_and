package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String user_id;
    private String content;
    private int hit;
    private int recommends;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.user_id = entity.getUser_id();
        this.hit = entity.getHit();
        this.recommends = entity.getRecommends().size();
        this.modifiedDate = entity.getModDate();
    }
}
