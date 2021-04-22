package com.testcode.yjp.last.repository.android;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Likes;
import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.android.AndBoardFindIdDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AndroidLikeRepository extends JpaRepository<Likes, Long> {

}
