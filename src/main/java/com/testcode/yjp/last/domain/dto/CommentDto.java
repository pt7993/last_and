package com.testcode.yjp.last.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {


    private Long id;
    private String user_id;
    private int like_check;
    private int dislike_check;
    private Long parentNum;
    private String comments;
    private LocalDateTime modifiedDate,regDate;



}
