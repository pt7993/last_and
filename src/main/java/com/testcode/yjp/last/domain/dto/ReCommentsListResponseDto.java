package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Comment;
import com.testcode.yjp.last.domain.ReComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReCommentsListResponseDto {

    private Long id;
    private String re_user_id; // id 작성자
    private Long re_parentCoNum; // 상위 댓글
    private String re_comments; // 하위 댓글
    private LocalDateTime modifiedDate;



    public ReCommentsListResponseDto(ReComment entity){
        this.id = entity.getId();
        this.re_user_id = entity.getRe_user_id();
        this.re_parentCoNum = entity.getRe_parentCoNum();
        this.re_comments = entity.getRe_comments();
        this.modifiedDate = entity.getModDate();
    }
}
