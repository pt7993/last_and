package com.testcode.yjp.last.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailDto {

    //발송할 이메일 내용(정보)를 저장할 Dto 생성
    private String address;
    private String title;
    private String message;
}
