package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CommentsListResponseDto {

    private Long id;
    private Long parentNum;
    private String comments;
    private String nickname;
    private LocalDateTime modifiedDate;

    public CommentsListResponseDto(Comments entity){
        this.id = entity.getId();
        this.parentNum = entity.getParentNum();
        this.comments = entity.getComments();
        this.nickname = entity.getNickname();
        this.modifiedDate = entity.getModDate();
    }
}
