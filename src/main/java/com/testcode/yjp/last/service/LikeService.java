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


    public int addLike(Long hb_num, Long member_id, Long cm_id, int count) {
        log.info("service addLike post");
        // 2. board에 id 를 뽑고 member에 user_id를 값을 받는다
        Board board = boardRepository.findById(hb_num).orElse(null);
        Member member = memberRepository.findById(member_id).orElse(null);
        Comment comment = commentsRepository.findById(cm_id).orElse(null);

        System.out.println("count값은 ="+count);
        if(!CheckLike(board, member, comment)){
            Likes like = new Likes(member, board, comment);
            likeRepository.save(like);
        }
//        System.out.println(board);
//        System.out.println(member);
//        System.out.println(comment);

        Optional<Member> byId = memberRepository.findById(member_id);
        Optional<Board> byBoardId = boardRepository.findById(hb_num);
        Optional<Comment> bycm_id = commentsRepository.findById(cm_id);

//        System.out.println(byId);
//        System.out.println(byBoardId);
//        System.out.println(bycm_id);
        Likes select = likeRepository.select(byId.get(), byBoardId.get(), bycm_id.get());
        Long id = likeRepository.select(byId.get(), byBoardId.get(), bycm_id.get()).getId();
        int callback = 0;
        System.out.println(likeRepository.select(byId.get(), byBoardId.get(), bycm_id.get()).getId());

        if (count == 0) {
            if (select.getLike_check() == 0 && select.getDislike_check() == 0) {
                likeRepository.likeUp(id);
                commentsRepository.like(cm_id);
                callback = 1;
                return callback;
            } else if (select.getLike_check() == 0 && select.getDislike_check() == 1) {
                likeRepository.dislikeChange(id);
                commentsRepository.dislikeChange(cm_id);
                callback = 2;
                return callback;
            } else if (select.getLike_check() == 1 && select.getDislike_check() == 0) {
                callback = 3;
                return callback;
            }
        }

        if (count == 1) {
            if (select.getLike_check() == 0 && select.getDislike_check() == 0) {
                likeRepository.disLikeUp(id);
                commentsRepository.dislike(cm_id);
                callback = 1;
                return callback;
            } else if (select.getLike_check() == 1 && select.getDislike_check() == 0) {
                likeRepository.likeChange(id);
                commentsRepository.likeChange(cm_id);
                callback = 2;
                return callback;
            } else if (select.getLike_check() == 0 && select.getDislike_check() == 1) {
                callback = 3;
                return callback;
            }
        }
        return callback;
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
