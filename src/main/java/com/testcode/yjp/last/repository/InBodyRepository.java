package com.testcode.yjp.last.repository;

import com.testcode.yjp.last.domain.InBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InBodyRepository extends JpaRepository<InBody, Long> {
}
