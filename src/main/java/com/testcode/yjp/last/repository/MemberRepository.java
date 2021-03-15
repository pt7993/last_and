package com.testcode.yjp.last.repository;

import com.testcode.yjp.last.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository  extends JpaRepository<Member,String> {


//    // 로그인 정보값
//    @Query("select m from Member m where user_id = :user_id and user_password = :user_password")
//    Member findMember(String user_id, String user_password);
//
    @Query("select m from Member m where user_id = :user_id")
    Member findByUser_id(String user_id);
}
