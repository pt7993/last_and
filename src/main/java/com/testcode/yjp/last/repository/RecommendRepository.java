package com.testcode.yjp.last.repository;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    Optional<Recommend> findByMemberAndBoard(Member member, Board board);


    @Query("select r from Recommend r order by r.id")
    List<Recommend> findAllDesc();
}
