package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


// 컨트롤러에서 뷰로 보내는 거 / 타임리프 값 model 써서 보낼용도
@Getter
@Setter
public class MemberFindIdDto {

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
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public MemberFindIdDto(Member member){
        this.id = member.getId();
        this.user_id = member.getUser_id();
        this.user_pw = member.getUser_pw();
        this.user_name = member.getUser_name();
        this.user_pn = member.getUser_pn();
        this.user_email = member.getUser_email();
        this.address_normal = member.getAddress_normal();
        this.address_detail = member.getAddress_detail();
        this.user_rrn = member.getUser_rrn();
        this.user_gender = member.getUser_gender();
        this.userRole = member.getUserRole();
    }
}
