package com.testcode.yjp.last.service;

import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.MailDto;
import com.testcode.yjp.last.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendEmailService {
/*
* createMailAndChangePassword : DTO에 사용자가 원하는 내용과 제목을 저장
updatePassword : 이메일로 발송된 임시비밀번호로 해당 유저의 패스워드 변경
->EncryptionUtils.encryptMD5(str) 는 비밀번호 암호화이다. (참고 : https://1-7171771.tistory.com/82)
getTempPassword : 10자리의 랜덤난수를 생성하는 메소드
* */

    private final MemberRepository memberRepository;

    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "ehgus8559@gmail.com";


    public MailDto createMailAndChangePassword(String user_email, String user_name) {
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(user_email);
        dto.setTitle(user_name + "님의 HOTTHINK 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. HOTTHINK 임시비밀번호 안내 관련 이메일 입니다." + "[" + user_name + "]" + "님의 임시 비밀번호는 "
                + str + " 입니다.");
        updatePassword(str, user_email);
        return dto;
    }

    public void updatePassword(String str, String user_email) {
        Long id = memberRepository.findByUser_email(user_email).getId();
//        memberRepository.updateMemberPassword(id,user_pw);
        memberRepository.updateMemberPassword(id, str);
    }


    public String getTempPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend(MailDto mailDto) {
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendEmailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        log.info("post sendEmail service");
        mailSender.send(message);
    }
}