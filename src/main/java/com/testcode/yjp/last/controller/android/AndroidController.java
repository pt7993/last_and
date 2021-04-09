package com.testcode.yjp.last.controller.android;

import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.MemberSoDto;
import com.testcode.yjp.last.domain.dto.android.AndMemberFindIdDto;
import com.testcode.yjp.last.domain.dto.android.AndMemberFindPwDto;
import com.testcode.yjp.last.domain.dto.android.AndMemberLoginDto;
import com.testcode.yjp.last.domain.dto.MemberJoinDto;
import com.testcode.yjp.last.domain.dto.android.AndMemberMypageDto;
import com.testcode.yjp.last.repository.android.AndroidRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.service.MemberService;
import com.testcode.yjp.last.service.android.AndMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/android")
public class AndroidController {

    private final MemberRepository memberRepository;
    private final AndroidRepository androidRepository;
    private final MemberService memberService;
    private final AndMemberService andMemberService;

    // 조회
    @GetMapping("/select")
    public List<Member> select() {
        log.info("AndroidController select 1st Line");

        return memberRepository.findAll();
    }

    //회원가입
    @PostMapping("/insert")
    public Long save(@RequestBody MemberJoinDto memberJoinDto) {
        log.info("AndroidController save 1st Line");

        return memberService.save(memberJoinDto);
    }

    // Json Id 중복검사
    @PostMapping("/idChk")
    public String IdChk(@RequestBody String user_id) throws Exception {
        log.info("AndroidController IdChk 1st Line");

        user_id = user_id.replaceAll("\\\"", "");
        String str = memberService.IdChk(user_id); // YES or NO
        return str;
    }

    //로그인
    @PostMapping("/signIn")
    public Member signIn(@RequestBody AndMemberLoginDto andMemberLoginDto) {
        log.info("AndroidController signIn 1st Line");

        return andMemberService.signIn(andMemberLoginDto);
    }

    //휴대폰 중복확인
    @PostMapping("/findPn")
    public Member findPn(@RequestBody String user_pn) {
        log.info("AndroidController findPn 1st Line");

        user_pn = user_pn.replaceAll("\\\"", "");
        Member member = androidRepository.findPn(user_pn);
        return member;
    }

    //아이디 찾기
    @PostMapping("/findId")
    public Member findId(@RequestBody AndMemberFindIdDto andMemberFindIdDto) {
        log.info("AndroidController findId 1st Line");

        return andMemberService.findId(andMemberFindIdDto);
    }

    //비밀번호 찾기
    @PostMapping("/findPw")
    public Member findPw(@RequestBody AndMemberFindPwDto andMemberFindPwDto) {
        log.info("AndroidController findPw 1st Line");

        return andMemberService.findPw(andMemberFindPwDto);
    }

    @PostMapping("/update/{id}")
    public Member updatePw(@PathVariable("id") Long id, @RequestBody String user_pw) {
        log.info("AndroidController updatePw 1st Line");

        return andMemberService.updatePw(id, user_pw);
    }

    @PostMapping("/socialInsert")
    public Member socialInsert(@RequestBody MemberSoDto memberSoDto) {
        log.info("AndroidController socialInsert 1st Line");

        return andMemberService.socialInsert(memberSoDto);
    }

    // 회원 수정
    @PutMapping("/mypage/{id}")
    public Member mypageUpdate(@PathVariable("id") Long id, @RequestBody AndMemberMypageDto andMemberMypageDto) {
        log.info("AndroidController mypageUpdate 1st Line");

        return mypageUpdate(id, andMemberMypageDto);
    }


    // 회원 탈퇴
    @DeleteMapping("/userDelete/{id}")
    public Long userDelete(@PathVariable("id") Long id) {
        log.info("AndroidController userDelete 1st Line");

        Member byId = memberRepository.findById(id).orElse(null);
        memberRepository.delete(byId);

        return byId.getId();
    }

    // 아이디로 회원 찾기
    @PostMapping("/findMem")
    public Member findMem(@RequestBody Long id) {
        log.info("AndroidController findMem 1st Line");

        Member byId = memberRepository.findById(id).orElse(null);

        return byId;
    }

}