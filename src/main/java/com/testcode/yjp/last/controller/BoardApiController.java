package com.testcode.yjp.last.controller;

import com.testcode.yjp.last.domain.Board;
import com.testcode.yjp.last.domain.Member;
import com.testcode.yjp.last.domain.dto.BoardSaveRequestDto;
import com.testcode.yjp.last.domain.dto.BoardUpdateRequestDto;
import com.testcode.yjp.last.repository.BoardRepository;
import com.testcode.yjp.last.repository.MemberRepository;
import com.testcode.yjp.last.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardApiController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    //등록기능
    @PostMapping("/trainerBoard/save/{id}")
    public Board save(@PathVariable Long id, @RequestBody Board board){
        Optional<Member> memberId = memberRepository.findById(id);
        board.setMember(memberId.get());
        boardRepository.save(board);
        return board;
    }

    //수정기능
    @PostMapping("/trainerBoard/update/{id}")
    public Long update(@PathVariable Long id,@RequestBody BoardUpdateRequestDto boardUpdateRequestDto){
        log.info("post update controller");
        System.out.println(id);
        System.out.println(boardUpdateRequestDto.getTitle());
        System.out.println(boardUpdateRequestDto.getContent());
        return boardService.update(id, boardUpdateRequestDto);
    }


    //삭제기능
    @PostMapping("/trainerBoard/delete/{id}")
    public Long delete(@PathVariable Long id){
        boardService.delete(id);
        return id;
    }

}
