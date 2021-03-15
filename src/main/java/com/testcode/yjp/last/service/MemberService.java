package com.testcode.yjp.last.service;


import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.domain.dto.MemberJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberJoinDto memberJoinDto) {
        return memberRepository.save(memberJoinDto.toEntity()).getId();
    }

    // 중복검사
    @Transactional
    public String IdChk(String user_id) {
        System.out.println(memberRepository.findByUser_id(user_id));
        if(memberRepository.findByUser_id(user_id) != null){
            return "YES";
        }else{
            return "NO";
        }
    }
}
