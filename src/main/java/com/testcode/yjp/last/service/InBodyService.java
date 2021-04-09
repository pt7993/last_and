package com.testcode.yjp.last.service;

import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.InBodyDto;
import com.testcode.yjp.last.repository.InBodyRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InBodyService {

    private final InBodyRepository inBodyRepository;
    private final MemberRepository memberRepository;

//
//    public Long save(Long id,InBodyDto inBodyDto) {
//        Optional<Member> result = memberRepository.findById(id);
//        inBodyDto.setMember(result.get());
//        log.info("InBodyService service post");
//        return inBodyRepository.save(inBodyDto.toEntity()).getId();
//    }
}
