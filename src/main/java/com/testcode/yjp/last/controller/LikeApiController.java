package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.repository.LikeRepository;
import com.testcode.yjp.last.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class LikeApiController {

    private final LikeService likeService;

    @PostMapping("/like/{boardLike}")
    public int likeAdd(@PathVariable("boardLike") Long hb_num, @RequestParam("likeId") Long member_id, @RequestParam("comId") Long cm_id) {
        System.out.println("게시글 번호"+hb_num);
        System.out.println("로그인 아이디"+member_id);
        System.out.println("댓글 대장"+cm_id);
        log.info("likeAdd controller post");

        int count = 0;

        return likeService.addLike(hb_num,member_id,cm_id,count);
    }

    @PostMapping("/dislike/{boardLike}")
    public int dislikeAdd(@PathVariable("boardLike") Long hb_num, @RequestParam("likeId") Long member_id,  @RequestParam("comId") Long cm_id) {
        log.info("likeAdd controller post");

        int count = 1;

        return likeService.addLike(hb_num,member_id,cm_id,count);
    }






}
