package com.testcode.yjp.last.repository;

import com.testcode.yjp.last.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {



    @Query("select c from Comment c order by c.id DESC ")
    List<Comment> findAllDesc();

//    @Query("select c from Comment c where c.board = :hb_num")
//    Comment findByParentNum(Long hb_num);
}
