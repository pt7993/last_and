package com.testcode.yjp.last.controller;


import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.MemberFindIdDto;
import com.testcode.yjp.last.service.MemberService;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberService memberService;



    @PostMapping("/api/idChk")
    public String IdChk(String user_id) throws Exception{
        System.out.println(user_id);
        String str = memberService.IdChk(user_id);
        return str;
    }
}
