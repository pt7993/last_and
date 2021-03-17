package com.testcode.yjp.last.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Inbody {
    @Id @GeneratedValue
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Member member;

    // 날짜

    @Column(name = "INBODY_WEIGHT", precision = 4, scale = 1)
    private BigDecimal weight;
    @Column(name = "INBODY_SMM", precision = 4, scale = 1)
    private BigDecimal smm;
    @Column(name = "INBODY_BFM", precision = 4, scale = 1)
    private BigDecimal bfm;
    @Column(name = "INBODY_BFP", precision = 4, scale = 1)
    private BigDecimal bfp;
}
