package com.testcode.yjp.last.repository;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Comments;
import com.testcode.yjp.last.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {



    @Query("select c from Comments c order by c.id DESC ")
    List<Comments> findAllDesc();

    @Query("select c from Comments c where c.parentNum = :ParentNum")
    Comments findByParentNum(Long ParentNum);
}
