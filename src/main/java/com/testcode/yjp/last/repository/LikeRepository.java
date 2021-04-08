package com.testcode.yjp.last.repository;


import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Comment;
import com.testcode.yjp.last.domain.Likes;
import com.testcode.yjp.last.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface LikeRepository extends JpaRepository<Likes, Long> {



    Optional<Likes> findByMemberAndBoardAndComment(Member member, Board board, Comment comment);

    @Query("select m from Likes m where m.member = :member_id and m.board= :board_id and m.comment= :comment_id")
    Likes select(Member member_id, Board board_id, Comment comment_id);

    @Modifying
    @Query("update Likes l set l.like_check = l.like_check+1, l.dislike_check=l.dislike_check-1  where l.id=:id")
    void dislikeChange(Long id);

    @Modifying
    @Query("update Likes l set l.like_check = l.like_check-1, l.dislike_check=l.dislike_check+1  where l.id=:id")
    void likeChange(Long id);

    @Modifying
    @Query("update Likes l set l.like_check = l.like_check+1 where l.id=:id")
    void likeUp(Long id);

    @Modifying
    @Query("update Likes l set l.dislike_check = l.dislike_check+1 where l.id=:id")
    void disLikeUp(Long id);

//
//    @Modifying
//    @Query("update Likes l set l.dislike_check = l.dislike_check+1 where l.user_id=:likeId")
//    void dislikeAdd(String likeId);
}



