package com.testcode.yjp.last.repository;

import com.testcode.yjp.last.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryTest {

    private final EntityManager em;

    public List<Member> delete(Long id, String user_pw) {
        return em.createQuery("select m from Member m where id = :id and user_pw = :user_pw", Member.class)
                .getResultList();
    }

    public void deleteId(Long id) {
        em.remove(id);
    }
}
