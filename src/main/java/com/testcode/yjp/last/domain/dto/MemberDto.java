package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String user_id;
    private String user_password;
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


}
