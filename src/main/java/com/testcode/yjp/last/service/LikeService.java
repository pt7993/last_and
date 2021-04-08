package com.testcode.yjp.last.service;

import com.testcode.yjp.last.domain.*;
import com.testcode.yjp.last.domain.dto.BoardListResponseDto;
import com.testcode.yjp.last.repository.BoardRepository;
import com.testcode.yjp.last.repository.CommentsRepository;
import com.testcode.yjp.last.repository.LikeRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LikeService {

    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentsRepository commentsRepository;


    public int addLike(Long boardLike, String likeId, Long comId) {
        log.info("service addLike post");
        // 2. board에 id 를 뽑고 member에 user_id를 값을 받는다
        Board board = boardRepository.findById(boardLike).orElse(null);
        Member member = memberRepository.findByUser_id(likeId);
        Comment comment = commentsRepository.findById(comId).orElse(null);

        int count;

        System.out.println(board);
        System.out.println(member);
        System.out.println(comment);
        System.out.println(comment.getLike_check());
        System.out.println(comment.getDislike_check());


        // 중복 좋아요 방지지
        if (CheckLike(board, member, comment)) {
            Likes like = new Likes(member, board, comment);
            likeRepository.save(like);
            commentsRepository.like(comId); //1
        } else {
            if (comment.getLike_check() == 1 && comment.getDislike_check() == 0) {
                count = 2;
                return count;
            }
            else if (comment.getLike_check() == 0 && comment.getDislike_check() == 0) {
                commentsRepository.like(comId); //1
                count = 1;
                return count;
            }
            else if (comment.getLike_check() == 0 && comment.getDislike_check() == 1) {
                commentsRepository.Rlike(comId);
                count = 3;
                return count;
            }
        }
        return 0;
    }

    public int disLike(Long boardLike, String likeId, Long comId) {
        log.info("service disLike post");
        Board board = boardRepository.findById(boardLike).orElse(null);
        Member member = memberRepository.findByUser_id(likeId);
        Comment comment = commentsRepository.findById(comId).orElse(null);
        System.out.println(board);
        System.out.println(member);
        System.out.println(comment);
        System.out.println(comment.getLike_check());
        System.out.println(comment.getDislike_check());
        int count;
        if (!CheckLike(board, member, comment)) {
            Likes like = new Likes(member, board, comment);
            likeRepository.save(like);
            commentsRepository.dislike(comId); //1
        } else {
            if (comment.getLike_check() == 1 && comment.getDislike_check() == 0) {
                commentsRepository.Clike(comId);
                count = 3;
                return count;
            }
            else if (comment.getLike_check() == 0 && comment.getDislike_check() == 0) {
                commentsRepository.dislike(comId); //1
                count = 1;
                return count;
            }
            else if (comment.getLike_check() == 0 && comment.getDislike_check() == 1) {
                count = 2;
                return count;
            }
        }
        return 0;
    }


    //  중복값 확인 메소드
    public boolean CheckLike(Board board, Member member, Comment comment) {
        Likes result = likeRepository.findByMemberAndBoardAndComment(member, board, comment).orElse(null);
        System.out.println("CheckLike=" + result);
        if (result != null) {
            return true;
        }
        return false;
    }


}
