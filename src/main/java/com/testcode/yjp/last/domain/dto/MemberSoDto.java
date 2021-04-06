package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSoDto {
    private String user_id;
    private String user_name;
    private String user_email;
    private String user_rrn;
    private String user_gender;

    public MemberSoDto(String user_id, String user_name, String user_email) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
    }

//    public MemberSoDto(String user_id, String user_name, String user_email, String user_rrn, String user_gender) {
//        this.user_id = user_id;
//        this.user_name = user_name;
//        this.user_email = user_email;
//        this.user_rrn = user_rrn;
//        this.user_gender = user_gender;
//    }

    public Member googleEntity() {
        return Member.builder()
                .user_id(user_id)
                .user_name(user_name)
                .user_email(user_email)
                .build();
    }

    public Member kakaoEntity() {
        return Member.builder()
                .user_id(user_id)
                .user_name(user_name)
                .user_email(user_email)
                .build();
    }
}
