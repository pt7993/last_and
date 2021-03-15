package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


// 컨트롤러에서 뷰로 보내는 거 / 타임리프 값 model 써서 보낼용도
@Getter
@Setter
public class MemberFindIdDto {

    private Long id;
    private String user_id;
    private String user_password;
    private String user_name;
    private int user_pn;
    private String user_email;
    private int user_addr;
    private String user_addr2;
    private String user_addr3;
    private String user_rrn;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public MemberFindIdDto(Member member){
        this.id = member.getId();
        this.user_id = member.getUser_id();
        this.user_password = member.getUser_password();
        this.user_name = member.getUser_name();
        this.user_pn = member.getUser_pn();
        this.user_email = member.getUser_email();
        this.user_addr = member.getUser_addr();
        this.user_addr2 = member.getUser_addr2();
        this.user_addr3 = member.getUser_addr3();
        this.user_rrn = member.getUser_rrn();
    }
}
