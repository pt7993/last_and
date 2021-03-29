package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.dto.MemberList;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.repository.PTUserRepository;
import com.testcode.yjp.last.service.PTUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/ptUser")
public class PTController {
    private final PTUserService ptUserService;
    private final PTUserRepository ptUserRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/view")
    public String trainerView() {
//        model.addAttribute("memberList", ptService.getTrainerList());
        return "ptUser/trainerView";
    }


    @GetMapping("/search")
    public String search(Model model, @RequestParam("search") String search, @RequestParam("head") String head) {
        log.info("controller Search = " + search);
        log.info("controller Head = " + head);

        List<MemberList> memberLists;

        if (head.equals("user_name")) {
            memberLists = ptUserService.nameSearch(search);
        } else if (head.equals("user_id")) {
            memberLists = ptUserService.idSearch(search);
        } else {
            memberLists = ptUserService.addrSearch(search);
        }

//        List<MemberList> memberLists = ptService.nameSearch(search);

        model.addAttribute("memberList", memberLists);
        return "/ptUser/trainerView";
    }

}
