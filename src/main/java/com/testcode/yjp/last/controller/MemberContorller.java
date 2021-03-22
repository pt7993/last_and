package com.testcode.yjp.last.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.MemberFindIdDto;
import com.testcode.yjp.last.domain.dto.MemberJoinDto;
import com.testcode.yjp.last.domain.dto.MemberUpdate;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberContorller {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "join/join";
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String save(MemberJoinDto memberJoinDto) {
        memberService.save(memberJoinDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login/loginView";
    }

    @PostMapping("/signIn")
    public String signIn(Model model, String user_id, String user_pw,  HttpServletRequest request, HttpServletResponse response) {
        Member member = memberRepository.findMember(user_id, user_pw);

        HttpSession session = (HttpSession) request.getSession();

        session.setAttribute("loginUser", member.getId());
        session.setAttribute("loginName", member.getUser_name());
//        @RequestParam("remember_me") boolean remember_me,
//        System.out.println(remember_me);
//        if(remember_me) {
//            Cookie cookie = new Cookie("remember_me", user_id);
//            cookie.setMaxAge(60 * 60 * 24 * 365);
//            cookie.setPath("/");
//            response.addCookie(cookie);
//        }
//        else {
//            Cookie cookie = new Cookie("remember_me", null);
//            cookie.setPath("/");
//            cookie.setMaxAge(0);
//            response.addCookie(cookie);
//        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String signOut(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        session.removeAttribute("loginUser");
        session.invalidate();
        return "redirect:";
    }

    // 회원 업데이트

    // 회원조회
    @GetMapping("/mypage/mypageSelect")
    public String memberList(Model model) {
        log.info("드,ㄹ러옴");
        model.addAttribute("memberList", memberService.getMemberList());

        return "mypage/mypageSelect";
    }

    @GetMapping("/mypage")
    public String memberUpdate(Long id, Model model) {
        MemberFindIdDto dto = memberService.findById(id);
        model.addAttribute("member", dto);

        return "mypage/mypageView";
    }

    @PostMapping("/mypage")
    public String update(Long id, MemberFindIdDto memberFindIdDto) {
        log.info("post mypage controller");
        System.out.println(id);
        memberService.update(id, memberFindIdDto);
        System.out.println(memberFindIdDto.getUser_pw());

        return "redirect:/";
    }

    @GetMapping("/IdPwCheck")
    public String IdPwCheck(){
        return "login/IdPwCheck";
    }

    // 회원탈퇴
    @GetMapping("/memberOut")
    public String memberOut(Long id) {
        log.info("memberout get Controller");
        System.out.println(id);
        return "mypage/memberOut";
    }

}