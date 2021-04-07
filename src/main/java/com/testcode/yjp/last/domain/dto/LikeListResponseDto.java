package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.Recommend;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LikeListResponseDto {
    private Long id;
    private Member member;
    private Board board;




    public LikeListResponseDto(Recommend entity){
        this.id = entity.getId();
        this.member = entity.getMember();
        this.board = entity.getBoard();
    }
}
