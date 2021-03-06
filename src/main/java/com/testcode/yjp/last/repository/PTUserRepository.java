package com.testcode.yjp.last.repository;

import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.PTUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PTUserRepository extends JpaRepository<PTUser, Long> {
    //name 검색
    @Query("select m from Member m where user_role like 'trainer' and m.user_name like %:user_name% ")
    List<Member> findTrainerN(String user_name);

    //id 검색
    @Query("select m from Member m where user_role like 'trainer' and m.user_id like %:user_id% ")
    List<Member> findTrainerI(String user_id);

    //헬스장 검색
    @Query("select m from Member m where user_role like 'trainer' and m.address_detail like %:address_detail%")
    List<Member> findTrainerA(String address_detail);

    //id로 select
    @Query("select m from Member m where member_id like %:member_id% ")
    List<Member> findMemberId(Long member_id);
}
