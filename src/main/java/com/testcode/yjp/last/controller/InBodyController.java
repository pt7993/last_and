package com.testcode.yjp.last.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inBody")
@Slf4j
public class InBodyController {


    @GetMapping("/register")
    public String inBody() {
        return "inBody/inBodyRegister";
    }

    @GetMapping("/inBodyResult")
    public String inBodyResult() {
        return "inBody/inBodyResult";
    }


}
