package com.testcode.yjp.last.repository;

import com.testcode.yjp.last.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, Long> {

    Files findByFno(int fno);
}
