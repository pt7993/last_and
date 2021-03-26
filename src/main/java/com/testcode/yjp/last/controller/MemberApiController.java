package com.testcode.yjp.last.controller;


import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.MemberFindIdDto;
import com.testcode.yjp.last.domain.dto.MemberUpdate;
import com.testcode.yjp.last.service.MemberService;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberService memberService;



    @PostMapping("/api/idChk")
    public String IdChk(String user_id) throws Exception{
        System.out.println(user_id);
        String str = memberService.IdChk(user_id);
        log.info("아이디체크로 들어옴");
        return str;
    }

    /*인증번호 전달받는 컨트롤러*/
    @GetMapping("/sendSMS")
    public @ResponseBody
    String sendSMS(String phoneNumber) {
        log.info("핸드폰 인증 시작");
        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }

        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);
        memberService.certifiedPhoneNumber(phoneNumber, numStr);
        return numStr;
    }


    @PostMapping ("/mypage/{id}")
    public String update(@PathVariable Long id , MemberUpdate memberUpdate) {
        log.info("put controller");
        memberService.update(id,memberUpdate);
        System.out.println(memberUpdate.getUser_pw());
        return "redirect:";
    }


}
