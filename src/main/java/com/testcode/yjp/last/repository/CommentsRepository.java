package com.testcode.yjp.last.repository;

import com.testcode.yjp.last.domain.Comment;
import com.testcode.yjp.last.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {


    @Query("select c from Comment c order by c.id DESC ")
    List<Comment> findAllDesc();


    @Query("select c from Comment c where c.parentNum = :hb_num")
    List<Comment> findAllCount(Long hb_num);


    @Modifying
    @Query("update Comment c set c.like_check = c.like_check+1 where c.id=:comId")
    void like(Long comId);

    @Modifying
    @Query("update Comment c set c.dislike_check = c.dislike_check+1 where c.id=:comId")
    void dislike(Long comId);

    @Modifying
    @Query("update Comment c set c.like_check = c.like_check+1, c.dislike_check=c.dislike_check-1  where c.id=:comId")
    void Rlike(Long comId);

    @Modifying
    @Query("update Comment c set c.like_check = c.like_check-1, c.dislike_check=c.dislike_check+1  where c.id=:comId")
    void Clike(Long comId);


//    @Modifying
//    @Query("update Comment c set c.like_check = c.like_check-1 where c.id=:comId")
//    void unlike(Long comId);

//
//    @Modifying
//    @Query("update Comment c set c.dislike_check = c.dislike_check-1 where c.id=:comId")
//    void undislike(Long comId);


//    @Query("select c from Comment c where c.board = :hb_num")
//    Comment findByParentNum(Long hb_num);
}
