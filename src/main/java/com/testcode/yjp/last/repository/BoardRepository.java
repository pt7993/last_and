package com.testcode.yjp.last.repository;

import com.testcode.yjp.last.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b order by b.id DESC ")
    List<Board> findAllDesc();
}
