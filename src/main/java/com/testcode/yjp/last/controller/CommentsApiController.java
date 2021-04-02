package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Comment;
import com.testcode.yjp.last.repository.BoardRepository;
import com.testcode.yjp.last.repository.CommentsRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Slf4j
public class CommentsApiController {

    private final MemberRepository memberRepository;
    private final CommentsRepository commentsRepository;
    private final CommentsService commentsService;
    private final BoardRepository boardRepository;

    @PostMapping("/save/{parentNum}")
    public Comment commentsSave(@PathVariable Long parentNum, @RequestBody Comment comments) {
        log.info("comments save Controller");
        System.out.println(comments.getUser_id());
        Optional<Board> boardId = boardRepository.findById(parentNum);
        comments.setBoard(boardId.get());
        commentsRepository.save(comments);
        return comments;
    }


    //수정기능
//    @PostMapping("/update/{id}")
//    public Long commentUpdate(@PathVariable Long id,@RequestBody CommentsUpdateRequestDto commentsUpdateRequestDto){
//        log.info("post comments update controller");
//        System.out.println(id);
//        return commentsService.update(id, commentsUpdateRequestDto);
//    }

    //삭제기능
    @PostMapping("/delete/{id}")
    public Long delete(@PathVariable Long id){
        commentsService.delete(id);
        return id;
    }

}
