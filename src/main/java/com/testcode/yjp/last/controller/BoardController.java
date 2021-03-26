package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.dto.BoardResponseDto;
import com.testcode.yjp.last.domain.dto.MemberFindIdDto;
import com.testcode.yjp.last.service.BoardService;
import com.testcode.yjp.last.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 전체조회
    @GetMapping("")
    public String BoardView(Model model) {
        model.addAttribute("boards", boardService.findAllDesc());
        return "/board/boardSelect";
    }

    // 저장페이지
    @GetMapping("/trainerBoard/save/{id}")
    public String trainerBoardSave(@PathVariable Long id,Model model) {
        return "board/boardView";
    }

    // 수정페이지
    @GetMapping("/trainerBoard/update/{id}")
    public String trainerBoardUpdate(@PathVariable Long id, Model model) {
        BoardResponseDto dto = boardService.findById(id);
        model.addAttribute("boards", dto);
        return "board/boardUpdate";
    }



}
