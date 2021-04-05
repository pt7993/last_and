package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Comment;
import com.testcode.yjp.last.domain.ReComment;
import com.testcode.yjp.last.repository.BoardRepository;
import com.testcode.yjp.last.repository.CommentsRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.repository.ReCommentsRepository;
import com.testcode.yjp.last.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recomments")
@Slf4j
public class ReCommentsApiController {
    private final ReCommentsRepository reCommentsRepository;
    private final BoardRepository boardRepository;
    private final CommentsRepository commentsRepository;

    @PostMapping("/save/{re_parentCoNum}")
    public ReComment RecommentsSave(@PathVariable Long re_parentCoNum, @RequestBody ReComment reComment) {
        log.info("Recomments save Controller");
        System.out.println(reComment.getRe_user_id());
        Optional<Comment> result = commentsRepository.findById(re_parentCoNum);
        reComment.setComment(result.get());
        reCommentsRepository.save(reComment);
        return reComment;
    }


    //수정기능
//    @PostMapping("/update/{id}")
//    public Long commentUpdate(@PathVariable Long id,@RequestBody CommentsUpdateRequestDto commentsUpdateRequestDto){
//        log.info("post comments update controller");
//        System.out.println(id);
//        return commentsService.update(id, commentsUpdateRequestDto);
//    }

    //삭제기능
//    @PostMapping("/delete/{id}")
//    public Long delete(@PathVariable Long id){
//        commentsService.delete(id);
//        return id;
//    }

}
