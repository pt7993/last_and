package com.testcode.yjp.last.domain.dto;

import com.testcode.yjp.last.domain.InBody;
import com.testcode.yjp.last.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InBodyDto {

    private Long id;
    private String inBody_user_id;
    private String inBody_weight;
    private String inBody_rmr;
    private String inBody_bfp;
    private String inBody_smm;
    private LocalDateTime regDate, modDate;
    private Member member;

    public InBody toEntity(){
        return InBody.builder()
                .id(id)
                .inBody_user_id(inBody_user_id)
                .inBody_weight(inBody_weight)
                .inBody_rmr(inBody_rmr)
                .inBody_bfp(inBody_bfp)
                .inBody_smm(inBody_smm)
                .build();
    }

}
