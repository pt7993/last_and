package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.InBody;
import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.InBodyDto;
import com.testcode.yjp.last.repository.InBodyRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.service.InBodyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inBody")
@Slf4j
public class InBodyApiController {

    private final InBodyService inBodyService;
    private final MemberRepository memberRepository;
    private final InBodyRepository inBodyRepository;

    @PostMapping("/register/{id}")
    public InBody inBodyRegister(@PathVariable Long id, @RequestBody InBody inBody) {
        log.info("InBodyApiController post ");
        System.out.println(id);
        Optional<Member> memberId = memberRepository.findById(id);
        inBody.setMember(memberId.get());
        inBodyRepository.save(inBody);


        return inBody;
    }



}
