package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.dto.MemberJoinDto;
import com.testcode.yjp.last.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class MemberContorller {

    private final MemberService memberService;

    // 회원가입
    @GetMapping("/join")
    public String join(){
        return "join/join";
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String save(MemberJoinDto memberJoinDto){
        memberService.save(memberJoinDto);
        return "redirect:/";
    }


}
