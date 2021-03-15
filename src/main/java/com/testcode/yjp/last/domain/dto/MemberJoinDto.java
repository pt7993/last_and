package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberJoinDto {

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
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .user_id(user_id)
                .user_password(user_password)
                .user_name(user_name)
                .user_pn(user_pn)
                .user_email(user_email)
                .user_addr(user_addr)
                .user_addr2(user_addr2)
                .user_addr3(user_addr3)
                .user_rrn(user_rrn)
                .build();
    }

    @Builder
    public MemberJoinDto(Long id,String user_id, String user_password, String user_name, int user_pn, String user_email, int user_addr, String user_addr2, String user_addr3){
        this.id=id;
        this.user_id =user_id;
        this.user_password =user_password;
        this.user_name=user_name;
        this.user_pn=user_pn;
        this.user_email=user_email;
        this.user_addr = user_addr;
        this.user_addr2 = user_addr2;
        this.user_addr3 = user_addr3;
    }
}
