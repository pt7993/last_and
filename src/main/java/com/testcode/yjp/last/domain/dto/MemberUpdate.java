package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberUpdate {

    private String user_pw;
    private String user_name;
    private String user_pn;
    private String user_email;
    private String address_normal;
    private String address_detail;
    private String user_rrn;
    private String user_gender;
    private String user_role;


    public MemberUpdate(Member member) {
        this.user_pw = member.getUser_pw();
        this.user_name = member.getUser_name();
        this.user_pn = member.getUser_pn();
        this.user_email = member.getUser_email();
        this.address_normal = member.getAddress_normal();
        this.address_detail = member.getAddress_detail();
        this.user_rrn = member.getUser_rrn();
        this.user_gender = member.getUser_gender();
        this.user_role = member.getUser_role();
    }

    public Member toEntity(){
        return Member.builder()
                .user_pw(user_pw)
                .user_name(user_name)
                .user_pn(user_pn)
                .user_email(user_email)
                .address_normal(address_normal)
                .address_detail(address_detail)
                .user_rrn(user_rrn)
                .user_gender(user_gender)
                .user_role(user_role)
                .build();
    }


    @Builder
    public MemberUpdate( String user_pw, String user_name, String user_pn, String user_email, String address_normal, String address_detail, String user_rrn, String user_gender, String user_role){
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_pn = user_pn;
        this.user_email = user_email;
        this.address_normal = address_normal;
        this.address_detail = address_detail;
        this.user_rrn = user_rrn;
        this.user_gender = user_gender;
        this.user_role = user_role;
    }
}
