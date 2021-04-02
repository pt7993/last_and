package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsUpdateRequestDto {

    private String comments;

    public CommentsUpdateRequestDto(Comment comments) {
        this.comments = comments.getComments();
    }

}
