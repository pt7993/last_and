package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberJoinDto {

    private Long id;
    private String user_id;
    private String user_pw;
    private String user_name;
    private Integer user_pn;
    private String user_email;
    private String address_normal;
    private String address_detail;
    private Integer user_rrn;
    private String user_gender;
    private UserRole userRole;
    private LocalDateTime regDate, modDate;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .user_id(user_id)
                .user_pw(user_pw)
                .user_name(user_name)
                .user_pn(user_pn)
                .user_email(user_email)
                .address_normal(address_normal)
                .address_detail(address_detail)
                .user_rrn(user_rrn)
                .user_gender(user_gender)
                .userRole(userRole.USER)
                .build();
    }


    @Builder
    public MemberJoinDto(Long id, String user_id, String user_pw, String user_name, Integer user_pn, String user_email,  String address_normal, String address_detail,Integer user_rrn,String user_gender,UserRole userRole){
        this.id = id;
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_pn = user_pn;
        this.user_email = user_email;
        this.address_normal = address_normal;
        this.address_detail = address_detail;
        this.user_rrn = user_rrn;
        this.user_gender = user_gender;
        this.userRole = userRole;
    }
}
