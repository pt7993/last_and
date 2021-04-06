package com.testcode.yjp.last.controller.android;

import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.MemberSoDto;
import com.testcode.yjp.last.domain.dto.android.AndMemberFindIdDto;
import com.testcode.yjp.last.domain.dto.android.AndMemberFindPwDto;
import com.testcode.yjp.last.domain.dto.android.AndMemberLoginDto;
import com.testcode.yjp.last.domain.dto.MemberJoinDto;
import com.testcode.yjp.last.repository.AndroidRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/android")
public class AndroidController {

    private final MemberRepository memberRepository;
    private final AndroidRepository androidRepository;
    private final MemberService memberService;

    // 조회
    @GetMapping("/select")
    public List<Member> select() {
        return memberRepository.findAll();
    }

    //회원가입
    @PostMapping("/insert")
    public Long save(@RequestBody MemberJoinDto memberJoinDto) {
        log.info(memberJoinDto.getUser_name());
        return memberService.save(memberJoinDto);
    }

    // Json Id 중복검사
    @PostMapping("/idChk")
    public String IdChk(@RequestBody String user_id) throws Exception {
        user_id = user_id.replaceAll("\\\"", "");
        log.info("user_id = " + user_id);
        String str = memberService.IdChk(user_id);
        return str;
    }

    //로그인
    @PostMapping("/signIn")
    public Member signIn(@RequestBody AndMemberLoginDto andMemberLoginDto) {
        String user_id = andMemberLoginDto.getUser_id();
        String user_pw = andMemberLoginDto.getUser_pw();
        Member member = memberRepository.findMember(user_id, user_pw);

        return member;
    }

    //휴대폰 중복확인
    @PostMapping("/findPn")
    public Member findPn(@RequestBody String user_pn) {
        user_pn = user_pn.replaceAll("\\\"", "");
        log.info(user_pn);
        Member member = androidRepository.findPn(user_pn);
        log.info(member.getUser_pn());
        return member;
    }

    //아이디 찾기
    @PostMapping("/findId")
    public Member findId(@RequestBody AndMemberFindIdDto andMemberFindIdDto) {
        String user_name = andMemberFindIdDto.getUser_name();
        String user_pn = andMemberFindIdDto.getUser_pn();

        log.info("name, pn = " + user_name + user_pn);
        Member user_id = androidRepository.findId(user_name, user_pn);
        return user_id;
    }

    //비밀번호 찾기
    @PostMapping("/findPw")
    public Member findPw(@RequestBody AndMemberFindPwDto andMemberFindPwDto) {
        String user_name = andMemberFindPwDto.getUser_name();
        String user_pn = andMemberFindPwDto.getUser_pn();
        String user_id = andMemberFindPwDto.getUser_id();

        Member member = androidRepository.findPw(user_name, user_pn, user_id);

        return member;
    }

    @PostMapping("/update/{id}")
    public Member updatePw(@PathVariable("id") Long id, @RequestBody String user_pw) {
        user_pw = user_pw.replaceAll("\\\"", "");
        Member member = memberRepository.findById(id).orElse(null);

        member.setUser_pw(user_pw);

        return memberRepository.save(member);
    }

    @PostMapping("/socialInsert")
    public Member socialInsert(@RequestBody MemberSoDto memberSoDto) {
        String user_id = memberSoDto.getUser_id();
        Member byUser_id = memberRepository.findByUser_id(user_id);
        if (byUser_id == null) {
            Member member = memberRepository.save(memberSoDto.googleEntity());
            return member;
        } else {
            return byUser_id;
        }

    }
}
