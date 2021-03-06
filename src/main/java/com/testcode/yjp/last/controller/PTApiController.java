package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.PTUser;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.repository.PTUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/ptUser")
public class PTApiController {
    private final MemberRepository memberRepository;
    private final PTUserRepository ptUserRepository;

    //신청 버튼 누르면 db에 ptUser 저장하는 컨트롤러
    @PostMapping(value = {"/apply/success/{member_id}/{trainer_id}"})
    public PTUser trainerApply(@PathVariable("member_id") Long member_id, @PathVariable("trainer_id") Long trainer_id, @RequestBody PTUser ptUser) {
        log.info("postMapping IN");
        Optional<Member> memberId = memberRepository.findById(member_id);
        Optional<Member> trainerId = memberRepository.findById(trainer_id);

        log.info("succ = "+memberId.get().getId());
        ptUser.setMember_id(memberId.get());
        ptUser.setTrainer_id(trainerId.get());
        ptUser.setAccept_condition("0"); // 신청 - 보류상태로 전환.
        log.info("ptuser.get user = " + ptUser.getMember_id().getId());
        log.info("ptuser.get tr = " + ptUser.getTrainer_id().getId());
        ptUserRepository.save(ptUser);
        return ptUser;
    }
}
