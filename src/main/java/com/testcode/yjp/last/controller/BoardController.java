package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.dto.BoardResponseDto;
import com.testcode.yjp.last.domain.dto.MemberFindIdDto;
import com.testcode.yjp.last.domain.dto.PageRequestDto;
import com.testcode.yjp.last.repository.CommentsRepository;
import com.testcode.yjp.last.service.BoardService;
import com.testcode.yjp.last.service.CommentsService;
import com.testcode.yjp.last.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentsService commentsService;

    // 전체조회
    @GetMapping("")
    public String BoardView(PageRequestDto pageRequestDto, Model model) {
        model.addAttribute("boards", boardService.findAllDesc());
        model.addAttribute("result", boardService.getList(pageRequestDto));
        model.addAttribute("PageRequestDto", pageRequestDto);

        return "/board/boardSelect";
    }

    // 저장페이지
    @GetMapping("/trainerBoard/save")
    public String trainerBoardSave(Long member_id,Model model) {
        log.info("save get Controller");
        System.out.println(member_id);
        return "board/boardView";
    }

    // 수정페이지
    @GetMapping("/trainerBoard/update")
    public String trainerBoardUpdate( Long hb_num, Model model, @ModelAttribute("PageRequestDto") PageRequestDto pageRequestDto) {
        BoardResponseDto dto = boardService.findById(hb_num);
        model.addAttribute("boards", dto);
        return "board/boardUpdate";
    }

    // 게시판 디테일 페이지
    @GetMapping("/trainerBoard/detail")
    public String trainerBoardDetail(Long hb_num , Model model, @ModelAttribute("PageRequestDto") PageRequestDto pageRequestDto) {

        model.addAttribute("boards", boardService.findById(hb_num));
        model.addAttribute("comments", commentsService.findAllDesc());
//        model.addAttribute("commentId", commentsService.findByComments(hb_num));

        boardService.updateView(hb_num);
        return "board/boardDetail";
    }



}
