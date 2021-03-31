package com.testcode.yjp.last.service;

import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.MemberList;
import com.testcode.yjp.last.repository.PTUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PTUserService {
    private final PTUserRepository ptUserRepository;

    //이름검색
    public List<MemberList> nameSearch(String search) {
        log.info("service = " + search);
        List<Member> trainer = ptUserRepository.findTrainerN(search);
        List<MemberList> memberLists = new ArrayList<>();

        for (Member member : trainer) {
            MemberList memberList = getMemberList(member);
            memberLists.add(memberList);
        }
        log.info("memberLists = " + memberLists.toString());

        return memberLists;
    }

    //id 검색
    public List<MemberList> idSearch(String search) {
        log.info("service = " + search);
        List<Member> trainer = ptUserRepository.findTrainerI(search);
        List<MemberList> memberLists = new ArrayList<>();

        for (Member member : trainer) {
            MemberList memberList = getMemberList(member);
            memberLists.add(memberList);
        }
        log.info("memberLists = " + memberLists.toString());

        return memberLists;
    }

    //헬스장 검색
    public List<MemberList> addrSearch(String search) {
        log.info("service = " + search);
        List<Member> trainer = ptUserRepository.findTrainerA(search);
        List<MemberList> memberLists = new ArrayList<>();

        for (Member member : trainer) {
            MemberList memberList = getMemberList(member);
            memberLists.add(memberList);
        }
        log.info("memberLists = " + memberLists.toString());

        return memberLists;
    }


    public List<MemberList> getMemberList(Long member_id) {
        List<Member> memberEntities = ptUserRepository.findMemberId(member_id);
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
                    .user_role(member.getUser_role())
                    .build();
            memberLists.add(memberList);
        }
        return memberLists;
    }

    private MemberList getMemberList(Member member) {
        return MemberList
                .builder()
                .id(member.getId())
                .user_name(member.getUser_name())
                .user_id(member.getUser_id())
                .user_pn(member.getUser_pn())
                .address_normal(member.getAddress_normal())
                .address_detail(member.getAddress_detail())
                .build();
    }

}
