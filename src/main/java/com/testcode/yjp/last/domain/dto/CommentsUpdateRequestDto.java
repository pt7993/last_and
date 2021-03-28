package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsUpdateRequestDto {

    private String comments;

    public CommentsUpdateRequestDto(Comments comments) {
        this.comments = comments.getComments();
    }

}
