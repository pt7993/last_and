package com.testcode.yjp.last.domain.dto;

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
    private int user_pn;
    private String user_email;
    private int user_addr;
    private String user_addr2;
    private String user_addr3;
    private LocalDateTime regDate;
    private LocalDateTime modDate;


}
