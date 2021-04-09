package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Comment;
import com.testcode.yjp.last.repository.BoardRepository;
import com.testcode.yjp.last.repository.CommentsRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Slf4j
public class CommentsApiController {

    private final CommentsRepository commentsRepository;
    private final CommentsService commentsService;
    private final BoardRepository boardRepository;

    @PostMapping("/save/{parentNum}")
    public Comment commentsSave(@PathVariable("parentNum") Long id, @RequestBody Comment comments) {
        log.info("comments save Controller");
        System.out.println(comments.getUser_id());

        Optional<Board> hb_num = boardRepository.findById(id);
        comments.setBoard(hb_num.get());
        commentsRepository.save(comments);

        return comments;
    }

    //삭제기능
    @PostMapping("/delete/{id}")
    public Long delete(@PathVariable("id") Long id){
        log.info("delete controller 들어옴");
        commentsService.delete(id);
        return id;
    }

}
