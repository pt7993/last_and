package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentResponseDto {

    private Long id;
    private String user_id;
    private int like_check;
    private int dislike_check;
    private Long parentNum;
    private String comments;
    private LocalDateTime modifiedDate,regDate;

    public CommentResponseDto(Comment entity) {
        this.id = entity.getId();
        this.user_id = entity.getUser_id();
        this.like_check = entity.getLike_check();
        this.dislike_check = entity.getDislike_check();
        this.parentNum = entity.getParentNum();
        this.comments = entity.getComments();
    }
}
