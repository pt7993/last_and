package com.testcode.yjp.last.service;


import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.MemberFindIdDto;
import com.testcode.yjp.last.domain.dto.MemberJoinDto;
import com.testcode.yjp.last.domain.dto.MemberList;
import com.testcode.yjp.last.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberJoinDto memberJoinDto) {

        return memberRepository.save(memberJoinDto.toEntity()).getId();
    }

    public MemberFindIdDto findIdDto(String id) {
        Member entity = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 정보가 없습니다. id=" + id));
        return new MemberFindIdDto(entity);
    }
//    public MemberResponseDto findById(String id) {
//        Member entity = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 정보가 없습니다. id="+id));
//        return new MemberResponseDto(entity);
//    }

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

    public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

        String api_key = "NCSE1LBEKZBYMTO4";
        String api_secret = "XDQAAVISPS9CJTKDVHWFA88MWTOBDQOS";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01039057545");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "휴대폰인증 테스트 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }

    public List<MemberList> getMemberList() {
        List<Member> memberEntities = memberRepository.findAll();
        List<MemberList> memberLists = new ArrayList<>();

        for (Member member : memberEntities) {
            MemberList memberList = MemberList
                    .builder()
                    .user_id(member.getUser_id())
                    .user_pw(member.getUser_pw())
                    .user_name(member.getUser_name())
                    .user_pn(member.getUser_pn())
                    .user_email(member.getUser_email())
                    .user_rrn(member.getUser_rrn())
                    .user_gender(member.getUser_gender())
                    .address_normal(member.getAddress_normal())
                    .address_detail(member.getAddress_detail())
                    .userRole(member.getUserRole())
                    .build();
            memberLists.add(memberList);
        }
        return memberLists;
    }
}
