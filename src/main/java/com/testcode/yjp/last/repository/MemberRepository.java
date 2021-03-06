package com.testcode.yjp.last.repository;

import com.testcode.yjp.last.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MemberRepository  extends JpaRepository<Member,Long> {


    //    // 로그인 정보값
//    @Query("select m from Member m where user_id = :user_id and user_password = :user_password")
//    Member findMember(String user_id, String user_password);
//
    @Query("select m from Member m where user_id = :user_id")
    Member findByUser_id(String user_id);

    // 로그인 정보값
    @Query("select m from Member m where user_id = :user_id and user_pw = :user_pw")
    Member findMember(String user_id, String user_pw);

    @Query("select m from Member m where user_name = :user_name and user_email = :user_email")
    Member findCheckId(String user_name, String user_email);

    @Query("select m from Member m where user_pw = :user_pw")
    Member findByUser_pw(String user_pw);

    @Query("select m from Member m where id = :id and user_pw = :user_pw")
    List<Member> findByMemberOut(Long id, String user_pw);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.user_pw= :user_pw WHERE m.id=:id")
    void updateMemberPassword(Long id, String user_pw);

    @Query("select m from Member m where user_email = :user_email")
    Member findByUser_email(String user_email);

    @Query("select m from Member m where user_id = :user_id and user_pw = :user_pw")
    List<Member> findByMemberIn(String user_id, String user_pw);
}