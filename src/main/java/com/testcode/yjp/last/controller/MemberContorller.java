package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.MemberJoinDto;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class MemberContorller {

    private final MemberService memberService;

    private final MemberRepository memberRepository;
    // 회원가입
    @GetMapping("/join")
    public String join(){
        return "join/join";
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String save(MemberJoinDto memberJoinDto){
        memberService.save(memberJoinDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login/loginView";
    }

    @PostMapping("/signIn")
    public String signIn(Model model, String user_id, String user_pw, HttpServletRequest request) {
        Member member = memberRepository.findMember(user_id, user_pw);
        HttpSession session = (HttpSession) request.getSession();
        session.setAttribute("loginUser", member.getUser_id());
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
    public String memberList(Model model){
        model.addAttribute("memberList", memberService.getMemberList());

        return "mypage/mypageSelect";
    }


}