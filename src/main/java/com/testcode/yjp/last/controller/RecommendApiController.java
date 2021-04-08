package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.service.LikeService;
import com.testcode.yjp.last.service.ReCommentsService;
import com.testcode.yjp.last.service.RecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class RecommendApiController {

    private final RecommendService recommendService;

    @PostMapping("/recommend/{boards_id}")
    public boolean recommendAdd(@PathVariable("boards_id") Long boards_id, @RequestParam("id") String recommendId) {
        System.out.println("게시글번호"+boards_id);
        System.out.println("로그인 아이디"+recommendId);
        log.info("recommendAdd controller post");
        return recommendService.recommend(boards_id, recommendId);
    }


}
