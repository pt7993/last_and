package com.testcode.yjp.last.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExCode {
    @Id
    @Column(name = "EX_CODE", length = 5)
    private String code;

    @Column(name = "EX_NAME")
    private String name;
}
