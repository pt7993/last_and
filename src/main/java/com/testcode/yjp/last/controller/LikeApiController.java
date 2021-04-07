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
    private final LikeRepository likeRepository;

    @PostMapping("/like/{boardLike}")
    public int likeAdd(@PathVariable("boardLike") Long boardLike, @RequestParam("likeId") String likeId, @RequestParam("comId") Long comId) {
        System.out.println("게시글 번호"+boardLike);
        System.out.println("로그인 아이디"+likeId);
        System.out.println("댓글 대장"+comId);
        log.info("likeAdd controller post");

        return likeService.addLike(boardLike,likeId,comId);
    }

    @PostMapping("/dislike/{boardLike}")
    public int dislikeAdd(@PathVariable("boardLike") Long boardLike, @RequestParam("likeId") String likeId,  @RequestParam("comId") Long comId) {
        System.out.println("게시글 번호"+boardLike);
        System.out.println("로그인 아이디"+likeId);
        log.info("likeAdd controller post");


        return likeService.disLike(boardLike,likeId,comId);
    }






}
