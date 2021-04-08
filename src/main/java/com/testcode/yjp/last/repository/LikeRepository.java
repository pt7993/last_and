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

//
//    @Modifying
//    @Query("update Likes l set l.dislike_check = l.dislike_check+1 where l.user_id=:likeId")
//    void dislikeAdd(String likeId);
}



